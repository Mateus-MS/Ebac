package cafeteiras;

import cafe.Cafe;

/**
 *
 */
public class Cafeteira2000 {

    private String marca;
    private String tipo;
    public ReservatorioDeAgua reservatorioDeAgua = new ReservatorioDeAgua();
    public ReservatorioDeCafe reservatorioDeCafe = new ReservatorioDeCafe();
    public Filtro filtro = new Filtro();

    public boolean estaLigada = false;

    /**
     * @param _marca por favor não use <strong>3 corações</strong>
     */
    public void selecionaMarca(String _marca){

        if(_marca.equals("3 corações")){
            throw new RuntimeException("Escolha uma marca de café descente.");
        }

        this.marca = _marca;

    }

    /**
     * Opções:
     *
     * <ul>
     *     <li>Expresso</li>
     *     <li>Cappuccino</li>
     *     <li>Latte</li>
     * </ul>
     */
    public void selecionaTipo(String _tipo){

        switch (_tipo){
            case "Expresso":
            case "Cappuccino":
            case "Latte":
                this.tipo = _tipo;
                break;
            default:
                throw new RuntimeException("O tipo de café selecionado não é uma opção válida.");
        }

    }

    /**
     * <strong>Atenção:</strong> o nível da água minimo é 40 <br>
     * Use para checar o nível da água
     * @see ReservatorioDeAgua#verNivelDaAgua()
     */
    public Cafe liga() throws InterruptedException {
        if(this.reservatorioDeAgua.nivel < 40){
            throw new RuntimeException("Nível da água muito baixo!");
        }

        this.estaLigada = true;
        Thread.sleep(60000);

        return this.desliga();
    }

    public Cafe desliga(){
        this.estaLigada = false;

        this.reservatorioDeCafe.nivel = 0;
        this.reservatorioDeAgua.nivel -= 40;
        this.filtro.estado = 2;

        return new Cafe(this.tipo, this.marca);
    }

    public void descreve(){

        System.out.println("O seu café " + this.tipo + " da marca " + this.marca + " está perfeito.");
        System.out.println("Agora você tem que arrumar a bagunça...");
        System.out.println("O nível da água está em: " + this.reservatorioDeAgua.nivel + " e o filtro está sujo.");

    }

    /**
     * @see Reservatorio
     */
    public class ReservatorioDeAgua extends Reservatorio{

        /**
         * @param quantidade Atenção para não transbordar. <br> Para ver quanto de água tem antes de adicionar mais use:
         * @see ReservatorioDeAgua#verNivelDaAgua()
         */
        public void adicionaAgua(int quantidade){
            this.adiciona(quantidade);

            if(this.nivel > 100){
                throw new RuntimeException("Adicionou água demais, Molhou tudo!!");
            }
        }

        /**
         * Retorna o nível atual da água :p
         */
        public int verNivelDaAgua(){
            return verQuantidade();
        }

    }

    /**
     * @see Reservatorio
     */
    public class ReservatorioDeCafe extends Reservatorio{

        /**
         * @param estadoDoFiltro Só é possível adicionar café com o filtro limpo <br>
         * @see Filtro#verOEstado()
         * @param quantidade Atenção para não colocar café demais. <br> Para ver quanto de café tem antes de adicionar mais use:
         * @see ReservatorioDeCafe#verQuantidadeDeCafe()
         */
        public void adicionaCafe(int quantidade, int estadoDoFiltro){
            if(estadoDoFiltro != 1){
                throw new RuntimeException("Você só pode adicionar café com o filtro limpo");
            }

            this.adiciona(quantidade);

            if(this.nivel > 100){
                throw new RuntimeException("Adicionou café demais, Espalhou em tudo!!");
            }
        }

        public int verQuantidadeDeCafe(){
            return this.verQuantidade();
        }
    }

    public class Filtro{

        private int estado = 2;

        /**
         * Retorna o estado atual do filtro
         *
         * <ul>
         *     <li>0 = vazio</li>
         *     <li>1 = limpo</li>
         *     <li>2 = usado</li>
         * </ul>
         *
         */
        public int verOEstado(){
            return this.estado;
        }

        /**
         * <strong>Atenção</strong>: você não consegue remover o filtro se não houver um filtro na cafeteira... duh. <br>
         * Ou se o filtro ainda estiver limpo.<br>
         * <br>
         * Para ver como o filtro está use.
         * @see Filtro#verOEstado()
         */
        public void remove(){
            if(this.estado == 0){
                throw new RuntimeException("Você não pode remover o filtro da cafeteira quando não há nenhum filtro na cafeteira.");
            }

            if(this.estado == 1){
                throw new RuntimeException("Sem desperdício, o filtro atual ainda está limpo...");
            }

            this.estado = 0;
        }

        /**
         * <strong>Atenção</strong>: Você só consegue adicionar um filtro quando não há nenhum.
         */
        public void adiciona(){
            if(this.estado != 0){
                throw new RuntimeException("Você não pode adicionar um filtro enquanto ainda há um fitro.");
            }

            this.estado = 1;
        }

    }

    private class Reservatorio{

        protected int nivel = 0;
        protected boolean estaCheio = false;

        protected void adiciona(int quantidade){
            this.nivel += quantidade;

            //Atualiza o estado de cheio do reservatório
            this.estaCheio = this.nivel >= 100;
        }

        protected int verQuantidade(){
            return this.nivel;
        }

    }

}
