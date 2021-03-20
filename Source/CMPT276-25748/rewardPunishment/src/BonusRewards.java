package Game;

public class BonusRewards extends interactable{
    if (IsExpired){
        RemoveExpiredRewards();
    }
    else{
        IncrementBonusRewards ();
        RemoveExpiredRewards();
    }
}
