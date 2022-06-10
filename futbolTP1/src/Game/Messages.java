package Game;

public enum Messages {


        INSUF_BALANCE("Inssuficient balance"),
        MAX_STADIUM_LEVEL("Stadium level is fully maximized"),
        OP_DONE("Operation succesfully done"),
        FULL_TEAM("Your team has no room to add a new athlete"),
        TURN("Turn of ");


        protected String msg;

        Messages(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }


