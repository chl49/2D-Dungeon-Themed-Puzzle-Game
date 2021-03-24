package Game;

public class ScoreManager {
    
    private int requiredRewards = 0;
    private int totalScore = 0;

    private static ScoreManager _instance = null;

    public static ScoreManager instance()
    {
        if(_instance == null)
        {
            _instance = new ScoreManager();
        }

        return _instance;
    }

    //
    public void addRequiredReward(int score)
    {
        requiredRewards++;
        totalScore += score;
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
}
