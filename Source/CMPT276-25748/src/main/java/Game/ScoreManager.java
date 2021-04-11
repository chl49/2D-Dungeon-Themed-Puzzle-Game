package Game;

/**
* ScoreManager class
* Tracks the total score and progress towards the require reward count
*/
public class ScoreManager {
    
    private int requiredRewards = 0;
    private int totalScore = 0;
    private int rewardsGoal = -1;

    public ScoreManager(int rewardsGoal)
    {
        this.rewardsGoal = rewardsGoal;
    }

    public void addRequiredReward(int score)
    {
        requiredRewards++;
        totalScore += score;
    }

    public void allRewardsCollected()
    {
        requiredRewards = rewardsGoal;
        totalScore = requiredRewards;
    }

    public void negativeScore()
    {
        requiredRewards=0;
        totalScore = -1;
    }

    public void addBonusReward(int score)
    {
        totalScore += score;
    }

    public void addPenalty(int score)
    {
        totalScore -= score;
    }

    public int getTotalScore()
    {
        return totalScore;
    }

    public int getRequiredRewardsCollected()
    {
        return requiredRewards;
    }

    public boolean hasReachedRewardsGoal()
    {
        return requiredRewards >= rewardsGoal;
    }
}
