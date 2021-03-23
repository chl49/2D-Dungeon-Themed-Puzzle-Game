package Game;

public class BonusRewards extends Rewards{
    protected float lifespan = 5;

    public BonusRewards (int inputPos, int inputScore){
        super(inputPos, inputScore);
    }

    public BonusRewards (int inputPos, int inputScore, float lifespan){
        super(inputPos, inputScore);
        this.lifespan = lifespan;
    }

    public void UpdateLifespan (float deltatime) {
        if (lifespan > 0.f) {
            lifespan -= deltatime;
        }
    }

    public boolean CheckExpire (){
        if (lifespan<0.f){
            return true;
        }
    }
}

