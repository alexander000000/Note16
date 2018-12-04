package av.shangin.lessons16;

class ManagerState {


    public enum StateType {
        Start, State_A, State_B, State_C, End
    }

    private StateType State;

    private static final ManagerState ourInstance = new ManagerState();

    static ManagerState getInstance() {
        return ourInstance;
    }

    private ManagerState() {
        this.State =StateType.Start;
    }

    public StateType getState(){
        return this.State;
    }

    public StateType nextState(){
        return this.State;
    }

    public StateType backState(){
        return this.State;
    }

    private StateType checkState(boolean Next){

            /*
          Next:
          Start->A
              A->C
              B->C
              C->End
            End->End

          Back:
          Start->Start
              A->Start
              B->Start
              C->B
            End->End
          */


        if (Next) {
            switch(this.State) {
                case Start:
                    return StateType.State_A;
                case State_A:
                    return StateType.State_C;
                case State_B:
                    return StateType.State_C;
                case State_C:
                    return StateType.End;
                case End:
                    return StateType.End;
            }
        }
        else {
            switch(this.State) {

                case Start:
                    return StateType.Start;
                case State_A:
                    return StateType.Start;
                case State_B:
                    return StateType.Start;
                case State_C:
                    return StateType.State_B;
                case End:
                    return StateType.End;
            }
        }

        return StateType.State_A;
    }


}
