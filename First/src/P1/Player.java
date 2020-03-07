package P1;
import P1.Cookies;
import P1.PrizePool;

public class Player {
    private PrizePool prize;  //记录个人所获得的奖励

    Player(){
        prize = new PrizePool();
    }

    public void Gain_Prize(Cookies prize_name){  //输入参数：获得的奖品名称
        prize.Add_Prize(prize_name);  //调用PrizePool类方法
    }

    public void DeleteAllPrize() {  //清空个人所得的所有礼物
        prize.Ini_PrizePool();    //调用PrizePool类方法
    }

    public void Show_Gain_Prizes()  //输出所获得的礼物
    {
        prize.Print_Prize();
    }
}
