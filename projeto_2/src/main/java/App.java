package main.java;

import main.java.client.Client;
import main.java.client.DAO.ClientDAO;
import main.java.client.DAO.ClientDAOMock;
import main.java.client.service.ClientService;
import main.java.exceptions.DataAlreadyInDBException;
import main.java.exceptions.DataNotPresentInDBException;
import main.java.funcionario.DAO.FuncionarioDAOMock;
import main.java.funcionario.Funcionario;
import main.java.funcionario.service.FuncionarioService;
import main.java.generics.DAO.IGenericDAO;
import main.java.generics.IGenericEntity;
import main.java.generics.service.IGenericService;
import main.java.product.DAO.ProductDAOMock;
import main.java.product.Product;
import main.java.product.service.ProductService;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class App {

    private IGenericDAO dao;
    private IGenericService service;

    private final List<String> fieldsNewClient = Arrays.asList("Insira seu nome:", "Insira sua idade:", "Insira seu endereço:", "Insira seu sexo: ( M | F )", "Insira seu cpf:");
    private final List<String> fieldsNewProduct = Arrays.asList("Insira o nome:", "Insira o código:", "Insira o preço:", "Insira há estoque: (Sim | Não)");
    private final List<String> fieldsNewFuncionario = Arrays.asList("Insira o nome:", "Insira o registro:", "Insira a idade:", "Insira o cargo:", "Insira o salário:");

    public String serviceSelected;
    public String acctionSelected;

    public App(){}

    public boolean selecionaServico(String service){
        //Com paciência o suficiente esse pode ser o único switch dessa classe
        //Usando a lógica que eu setei no começo de mensagem guardadas em váriaveis
        // Quem quiser refatorar :D

        switch (service){
            case "1":
                this.dao = ClientDAO.getInstance();
                this.service = new ClientService(this.dao);
                break;
            case "2":
                this.dao = ProductDAOMock.getInstance();
                this.service = new ProductService(this.dao);
                break;
            case "3":
                this.dao = FuncionarioDAOMock.getInstance();
                this.service = new FuncionarioService(this.dao);
                break;
            case "4":
                this.serviceSelected = service;
                return true;
            default:
                mostraMensagem("Erro", "Opção inválida...");
                return false;
        }

        this.serviceSelected = service;
        return true;
    }

    public boolean selecionaAcction(String acction){
        int converted = Integer.parseInt(acction);

        if(converted < 1 || converted > 4){
            mostraMensagem("Erro", "Opção inválida...");
            return false;
        }

        this.acctionSelected = acction;
        return true;
    }

    public void tentaRegistrar(){
        //Define a sequencia de campos que será utilizado a seguir de acordo com o serviço selecionado
        List<String> fieldsNewSelected = switch (this.serviceSelected){
            case "1" -> this.fieldsNewClient;
            case "2" -> this.fieldsNewProduct;
            case "3" -> this.fieldsNewFuncionario;
            default -> throw new RuntimeException();
        };

        //Pega a entrada do usuário
        List<String> fieldsValue = fieldsNewSelected.stream().map(field -> mostraPergunta("Cadastro", field)).toList();

        //Testa se já existe uma entidade com esse identificador no
        if(switch (this.serviceSelected) {
            case "1" -> this.service.pesquisa(Double.parseDouble(fieldsValue.get(4))) != null;
            case "2", "3" -> this.service.pesquisa(Double.parseDouble(fieldsValue.get(1))) != null; //Por coincidência tanto o produto quanto o funcionario o identificador fica no 1
            default -> false;
        }){
            return;
        }

        //Cria uma nova entity
        IGenericEntity newEntity = switch (this.serviceSelected){
            case "1" -> createEntity(fieldsValue, Client.class);
            case "2" -> createEntity(fieldsValue, Product.class);
            case "3" -> createEntity(fieldsValue, Funcionario.class);
            default -> throw new RuntimeException();
        };

        //Tenta registrar
        try {
            //Mostra a mensagem de sucsso
            if(service.register(newEntity)){
                //de acordo com o serviço selecionado
                switch (this.serviceSelected){
                    case "1" -> mostraMensagem("Sucesso", "Cliente registrado com sucesso");
                    case "2" -> mostraMensagem("Sucesso", "Produto registrado com sucesso");
                    case "3" -> mostraMensagem("Sucesso", "Funcionario registrado com sucesso");
                }
            }
        } catch (DataAlreadyInDBException e) {
            //Mostra mensagem de erro de acordo com o serviço selecionado
            switch (this.serviceSelected){
                case "1" -> mostraMensagem("Erro", "Cliente já registrado");
                case "2" -> mostraMensagem("Erro", "Produto já registrado");
                case "3" -> mostraMensagem("Erro", "Funcionario já registrado");
            }
        }
    }

    public void tentaPesquisar(){

        //Define a mensagem de acordo com o serviço selecionado
        String messageSearch = switch (this.serviceSelected){
            case "1" -> "Insira o cpf que quer procurar:";
            case "2" -> "Insira o código que quer procurar:";
            case "3" -> "Insira o registro que quer procurar:";
            default -> throw new RuntimeException();
        };

        //Guarda o code recebido pelo usuário
        Double code = Double.parseDouble(mostraPergunta("Pesquisando", messageSearch));

        //TODO validar cada code:
        // Validar como cpf se for um cliente
        // Validar como codigo se for um produto
        // Validar como registro se for um funcionario

        //Pesquisa no banco de dados
        Object entity = this.service.pesquisa(code);

        //Caso encontre
        if(entity != null){
            //Mostra a mensagem de acordo com o serviço selecionado
            switch (this.serviceSelected){
                case "1" -> mostraMensagem("Cliente encontrado",     entity.toString());
                case "2" -> mostraMensagem("Produto encontrado",     entity.toString());
                case "3" -> mostraMensagem("Funcionario encontrado", entity.toString());
            }

            //Caso não encontre
        } else {
            //Mostra a mensagem de acordo com o serviço selecionado
            switch (this.serviceSelected){
                case "1" -> mostraMensagem("Erro", "Cliente não encontrado");
                case "2" -> mostraMensagem("Erro", "Produto não encontrado");
                case "3" -> mostraMensagem("Erro", "Funcionario não encontrado");
            }
        }
    }

    public void tentaExcluir(){
        //Define a mensagem de acordo com o serviço selecionado
        String message = switch (this.serviceSelected){
            case "1" -> "Insira o cpf que quer excluir:";
            case "2" -> "Insira o código que quer excluir:";
            case "3" -> "Insira o registro que quer excluir:";
            default -> throw new RuntimeException();
        };

        //Define o titulo de acordo com o serviço selecionado
        String title = switch (this.serviceSelected){
            case "1" -> "Exluindo cliente";
            case "2" -> "Exluindo produto";
            case "3" -> "Exluindo funcionario";
            default -> throw new RuntimeException();
        };

        //Guarda o code de quem deve ser excluido recebido pelo usuário
        Double code =  Double.parseDouble(mostraPergunta(title, message));

        try {
            //Exclui a entidade do banco de dados
            service.excluir(code);
            //Mostra uma mensagem de sucesso de acordo com o serviço selecionado.
            switch (this.serviceSelected){
                case "1": mostraMensagem("Sucesso", "Cliente excluido do banco de dados.");
                case "2": mostraMensagem("Sucesso", "Produto excluido do banco de dados.");
                case "3": mostraMensagem("Sucesso", "Funcionario excluido do banco de dados.");
            }
            JOptionPane.showConfirmDialog(
                    null,
                    "Excluido com sucesso!",
                    "Excluido.",
                    JOptionPane.DEFAULT_OPTION
            );
        } catch (DataNotPresentInDBException e){
            //Define a mensagem de erro de acordo com o serviço selecionado
            String erroMessage = switch (this.serviceSelected){
                case "1" -> "Não é possível excluir um cliente que não existe no banco de dados.";
                case "2" -> "Não é possível excluir um produto que não existe no banco de dados.";
                case "3" -> "Não é possível excluir um funcionario que não existe no banco de dados.";
                default -> throw new RuntimeException();
            };
            //De fato mostra a mensagem
            mostraMensagem("Erro", erroMessage);
        }

    }

    public void tentaAtualizar(){
        //Define a mensagem de acordo com o serviço selecionado
        String message = switch (this.serviceSelected){
            case "1" -> "Insira o cpf que quer atualizar:";
            case "2" -> "Insira o código que quer atualizar:";
            case "3" -> "Insira o registro que quer atualizar:";
            default -> throw new RuntimeException();
        };

        //Define o titulo de acordo com o serviço selecionado
        String title = switch (this.serviceSelected){
            case "1" -> "Atualizar cliente";
            case "2" -> "Atualizando produto";
            case "3" -> "Atualizando funcionario";
            default -> throw new RuntimeException();
        };

        //Guarda o code de quem deve ser atualizado recebido pelo usuário
        Double code = Double.parseDouble(mostraPergunta(title, message));

        //Define a sequencia de campos que será utilizado a seguir de acordo com o serviço selecionado
        //TODO mensagens diferentes pra quando estiver atualizando
        List<String> fieldsNewSelected = switch (this.serviceSelected){
            case "1" -> this.fieldsNewClient;
            case "2" -> this.fieldsNewProduct;
            case "3" -> this.fieldsNewFuncionario;
            default -> throw new RuntimeException();
        };

        //Se a entidade não existir no banco de dados
        if(this.service.pesquisa(code) == null){
            //Define a mensagem de erro de acordo com o serviço selecionado
            String erroMessage = switch (this.serviceSelected){
                case "1" -> "Não é possível atualizar um cliente que não existe no banco de dados.";
                case "2" -> "Não é possível atualizar um produto que não existe no banco de dados.";
                case "3" -> "Não é possível atualizar um funcionario que não existe no banco de dados.";
                default -> throw new RuntimeException();
            };

            //De fato mostra a mensagem
            mostraMensagem("Erro", erroMessage);
            return;
        }
        //Pega a entrada do usuário
        List<String> fieldsValue = fieldsNewSelected.stream().map(field -> mostraPergunta("", field)).toList();

        //Cria uma nova entidade de acordo com o serviço selecionado
        IGenericEntity newEntity = switch (this.serviceSelected) {
            case "1" -> createEntity(fieldsValue, Client.class);
            case "2" -> createEntity(fieldsValue, Product.class);
            case "3" -> createEntity(fieldsValue, Funcionario.class);
            default -> throw new RuntimeException();
        };

        //TODO perguntar se o usuário realmente quer atualizar os dados, mostrando os dados atuais e os novos

        //Atualiza os dados
        service.atualiza(newEntity);

    }

    public void   mostraMensagem(String title, String text){
        JOptionPane.showConfirmDialog(
                null,
                text,
                title,
                JOptionPane.DEFAULT_OPTION
        );
    }

    public String mostraPergunta(String title, String text){
        return JOptionPane.showInputDialog(
                null,
                text,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private <T extends IGenericEntity> T createEntity(List<String> data, Class<T> reference){

        if(reference.equals(Client.class)){
            return reference.cast(new Client(
                    data.get(0),
                    Integer.parseInt(data.get(1)),
                    data.get(2),
                    data.get(3).charAt(0),
                    Double.parseDouble(data.get(4))
            ));
        }

        if(reference.equals(Product.class)){
            boolean state = data.get(3).toLowerCase().charAt(0) == 's';
            return reference.cast(new Product(
                    data.get(0),
                    Double.parseDouble(data.get(1)),
                    Float.parseFloat(data.get(2)),
                    state
            ));
        }

        if(reference.equals(Funcionario.class)){
            return reference.cast(new Funcionario(
                    data.get(0),
                    Double.parseDouble(data.get(1)),
                    Integer.parseInt(data.get(2)),
                    data.get(3),
                    Integer.parseInt(data.get(4))
            ));
        }

        //TODO criar um custom excpetion
        throw new RuntimeException();

    }

}
