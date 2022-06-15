package Game;

public enum Messages {


        INSUF_BALANCE("Inssuficient balance"),
        MAX_STADIUM_LEVEL("Stadium level is fully maximized"),
        OP_DONE("Operation succesfully done"),
        FULL_TEAM("Your team has no room to add a new athlete"),
        TURN("Turn of "),
        FILE_NOT_FOUND("this file is empty"),
        TIE("It´s a tie "),
        WON(" won the match"),
        SCORED_BY(" scored by "),
        NOT_ENOUGH_PLAYERS("not enough players"),
        EXIT ("Return to previous menu"),
        SELECT_ATHLETE ("Select an athlete"),
        INVALID ("Invalid option");


        protected String msg;

        Messages(String msg) {
                this.msg = msg;
        }

        public String getMsg() {
                return msg;
        }
}


