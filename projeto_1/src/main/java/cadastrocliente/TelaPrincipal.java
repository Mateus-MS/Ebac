package cadastrocliente;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal extends javax.swing.JFrame {

    public IClientDAO clientCollection = new ClientMapDAO();
    private DefaultTableModel modelo = new DefaultTableModel();

    public TelaPrincipal() {
        initComponents();
        
        this.modelo.addColumn("Nome");
        this.modelo.addColumn("CPF");
        this.modelo.addColumn("Telefone");
        this.modelo.addColumn("Endereço");
        this.modelo.addColumn("Estado");
        this.modelo.addColumn("Cidade");
        this.tabelaClientes.setModel(this.modelo);
        
        //Sample data
        this.cadastrar(new Client("Mateus Alves", "123.456.789-12", "99 999999999", "R. Prof. Palm", "Roraima", "Caracaraí"));
        this.cadastrar(new Client("Lucas Sousa", "987.654.321-21", "88 888888888", "R. Salamanca", "São Paulo", "Ubatuba"));
        this.cadastrar(new Client("Khelbia Terminelis", "333.333.333-25", "12 123123123", "Santo, centro", "São Paulo", "Campos de jordão"));
    }
    
    public void unselectAllFields(){
        this.jFieldNome.setText("");
        this.jFieldCPF.setText("");
        this.jFieldTelefone.setText("");
        this.jFieldEndereco.setText("");

        this.estadoSeletor.setSelectedIndex(2);
    }

    public void populateAllFields(String nome, String cpf, String telefone, String endereco, String estado, String cidade){
        this.jFieldNome.setText(nome);
        this.jFieldCPF.setText(cpf);
        this.jFieldTelefone.setText(telefone);
        this.jFieldEndereco.setText(endereco);
        this.estadoSeletor.setSelectedItem(estado);
        this.cidadeSeletor.setSelectedItem(cidade);
    }
    
    public int getRowOfClient(Client client){
        int selectedRow = -1;
        for(int i = 0; i < this.modelo.getRowCount(); i++){
            String value = (String) this.modelo.getValueAt(i, 1);

            if(value.equals(client.getCPF())){
                selectedRow = i;
                break;
            }
        }
        return selectedRow;
    }
    
    public void cadastrar(Client client){
        if(this.clientCollection.cadastrar(client)){
            this.modelo.addRow(new Object[]{client.getNome(), client.getCPF(), client.getTelefone(), client.getEndereco(), client.getEstado(), client.getCidade()});
            this.unselectAllFields();
            return;
        }
        
        
        
        Client clientDataFromServer = this.clientCollection.getClient(client.getCPF());
        Client clientDataFromForm   = client;
        boolean needAtt = !clientDataFromServer.equalsTo(clientDataFromForm);
        
        int option = -1;
        if(needAtt){
            option = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja atualizar o cadastro do cliente?",
                "CPF já cadastrado",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        
        //sim
        if(option == 0){
            int selectedRow = this.getRowOfClient(client);
            
            this.clientCollection.attClient(client);
            this.modelo.setValueAt(client.getNome(), selectedRow, 0);
            this.modelo.setValueAt(client.getTelefone(), selectedRow, 2);
            this.modelo.setValueAt(client.getEndereco(), selectedRow, 3);
            this.modelo.setValueAt(client.getEstado(), selectedRow, 4);
            this.modelo.setValueAt(client.getCidade(), selectedRow, 5);
        }
        
    }

    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanelHolder = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jFieldNome = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jFieldCPF = new javax.swing.JTextField();
        jFieldTelefone = new javax.swing.JTextField();
        jFieldEndereco = new javax.swing.JTextField();
        estadoSeletor = new javax.swing.JComboBox<>();
        cidadeSeletor = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(94, 94, 94));

        jPanelHolder.setBackground(new java.awt.Color(59, 59, 59));
        jPanelHolder.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        tabelaClientes.setBackground(new java.awt.Color(59, 59, 59));
        tabelaClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tabelaClientes.setForeground(new java.awt.Color(255, 255, 255));
        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaClientes.setFocusable(false);
        tabelaClientes.setGridColor(new java.awt.Color(59, 59, 59));
        tabelaClientes.setRequestFocusEnabled(false);
        tabelaClientes.setRowHeight(50);
        tabelaClientes.setRowSelectionAllowed(false);
        tabelaClientes.setSelectionBackground(new java.awt.Color(55, 55, 55));
        tabelaClientes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabelaClientes.setShowGrid(true);
        tabelaClientes.setShowHorizontalLines(true);
        tabelaClientes.setShowVerticalLines(true);
        tabelaClientes.getTableHeader().setReorderingAllowed(false);
        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaClientes);
        tabelaClientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabelaClientes.getColumnModel().getColumnCount() > 0) {
            tabelaClientes.getColumnModel().getColumn(0).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(1).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(2).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(3).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(4).setResizable(false);
            tabelaClientes.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanelHolderLayout = new javax.swing.GroupLayout(jPanelHolder);
        jPanelHolder.setLayout(jPanelHolderLayout);
        jPanelHolderLayout.setHorizontalGroup(
            jPanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHolderLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelHolderLayout.setVerticalGroup(
            jPanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jFieldNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFieldNome.setForeground(new java.awt.Color(59, 59, 59));
        jFieldNome.setText("Mateus Alves de Sousa");
        jFieldNome.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 2), "Nome", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldNomeActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(59, 59, 59));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Salvar");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButton(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(59, 59, 59));
        jLabel11.setText("Controle de clientes");

        jFieldCPF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFieldCPF.setForeground(new java.awt.Color(59, 59, 59));
        jFieldCPF.setText("033.355.551-25");
        jFieldCPF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 2), "CPF", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jFieldCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldCPFActionPerformed(evt);
            }
        });

        jFieldTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFieldTelefone.setForeground(new java.awt.Color(59, 59, 59));
        jFieldTelefone.setText("95 991545248");
        jFieldTelefone.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 2), "Telefone", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jFieldTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldTelefoneActionPerformed(evt);
            }
        });

        jFieldEndereco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFieldEndereco.setForeground(new java.awt.Color(59, 59, 59));
        jFieldEndereco.setText("R. Prof. Dr. Antonio");
        jFieldEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 2), "Endereço", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jFieldEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldEnderecoActionPerformed(evt);
            }
        });

        estadoSeletor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Roraima", "São Paulo", " " }));
        estadoSeletor.setSelectedIndex(2);
        estadoSeletor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 2), "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(59, 59, 59))); // NOI18N
        estadoSeletor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoSeletorActionPerformed(evt);
            }
        });

        cidadeSeletor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boa Vista", "Caracarai", " " }));
        cidadeSeletor.setSelectedIndex(2);
        cidadeSeletor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 2), "Cidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(59, 59, 59))); // NOI18N

        jSeparator1.setForeground(new java.awt.Color(59, 59, 59));

        jButton3.setBackground(new java.awt.Color(246, 76, 76));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Excluir");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton(evt);
            }
        });

        jButton1.setText("Limpar campos");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFieldNome, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jFieldEndereco, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estadoSeletor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cidadeSeletor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estadoSeletor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cidadeSeletor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelHolder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }//

    private void jFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {}

    private void confirmButton(java.awt.event.ActionEvent evt) {
        
        //Validate Fields
        String nome = this.jFieldNome.getText();
        if("".equals(nome)){
            JOptionPane.showMessageDialog(
                    null,
                    "Para cadastrar um cliente é preciso fornecer um nome.",
                    "Campo vazio",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        String cpf = this.jFieldCPF.getText();
        if("".equals(cpf)){
            JOptionPane.showMessageDialog(
                    null,
                    "Para cadastrar um cliente é preciso fornecer um CPF.",
                    "Campo vazio",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        String telefone = this.jFieldTelefone.getText();
        if("".equals(telefone)){
            JOptionPane.showMessageDialog(
                    null,
                    "Para cadastrar um cliente é preciso fornecer um telefone.",
                    "Campo vazio",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        String endereco = this.jFieldEndereco.getText();
        if("".equals(endereco)){
            JOptionPane.showMessageDialog(
                    null,
                    "Para cadastrar um cliente é preciso fornecer um telefone.",
                    "Campo vazio",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        String estado = this.estadoSeletor.getSelectedItem().toString();
        if(" ".equals(estado)){
            JOptionPane.showMessageDialog(
                    null,
                    "Para cadastrar um cliente é preciso fornecer um estado.",
                    "Campo vazio",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        
        String cidade = this.cidadeSeletor.getSelectedItem().toString();
        if(" ".equals(cidade)){
            JOptionPane.showMessageDialog(
                    null,
                    "Para cadastrar um cliente é preciso fornecer uma cidade.",
                    "Campo vazio",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        this.cadastrar(new Client(nome, cpf, telefone, endereco, estado, cidade));
        
    }

    private void jFieldCPFActionPerformed(java.awt.event.ActionEvent evt) {}

    private void jFieldTelefoneActionPerformed(java.awt.event.ActionEvent evt) {}

    private void jFieldEnderecoActionPerformed(java.awt.event.ActionEvent evt) {}

    private void deleteButton(java.awt.event.ActionEvent evt) {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir o cadastro do cliente?",
                "Atenção!",
                JOptionPane.INFORMATION_MESSAGE
            );
        
        if(option == 0){
            String cpf = this.jFieldCPF.getText();
            this.clientCollection.removeClient(cpf);
            this.modelo.removeRow(this.tabelaClientes.getSelectedRow());
        }
    }

    private void estadoSeletorActionPerformed(java.awt.event.ActionEvent evt) {
        String[] cidades = new String[]{};

        switch(this.estadoSeletor.getSelectedItem().toString()){
            case "Roraima"   -> cidades = new String[]{"Boa Vista", "Caracaraí"};
            case "São Paulo" -> cidades = new String[]{"Campos de jordão", "Ubatuba"};
        }

        this.cidadeSeletor.setModel(new javax.swing.DefaultComboBoxModel<>(cidades));
    }

    private void tabelaClientesMouseClicked(java.awt.event.MouseEvent evt) {
        int linhaSelecionada = this.tabelaClientes.getSelectedRow();
        
        String nome = (String) this.tabelaClientes.getValueAt(linhaSelecionada, 0);
        String cpf = (String) this.tabelaClientes.getValueAt(linhaSelecionada, 1);
        String telefone = (String) this.tabelaClientes.getValueAt(linhaSelecionada, 2);
        String endereco = (String) this.tabelaClientes.getValueAt(linhaSelecionada, 3);
        String estado = (String) this.tabelaClientes.getValueAt(linhaSelecionada, 4);
        String cidade = (String) this.tabelaClientes.getValueAt(linhaSelecionada, 5);
        
        populateAllFields(nome, cpf, telefone, endereco, estado, cidade);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.unselectAllFields();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JComboBox<String> cidadeSeletor;
    private javax.swing.JComboBox<String> estadoSeletor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField jFieldCPF;
    private javax.swing.JTextField jFieldEndereco;
    private javax.swing.JTextField jFieldNome;
    private javax.swing.JTextField jFieldTelefone;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelHolder;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tabelaClientes;
}
