package P1;
import P1.Cookies;
import P1.Constant;

public class PrizePool {
    int[] prize;  //1:一秀，2：二举，3：三红，4：四进，5：对堂，6：状元

    public PrizePool(){      //构造函数，默认为0
        prize = new int[Constant.PRIZECNT + 1];
        for(int i = 0; i< Constant.PRIZECNT + 1; i++)
        {
            prize[i] = 0;
        }
    }

    public PrizePool(int[] Prize){
        prize = new int[Constant.PRIZECNT + 1];
        for(int i = 1; i < prize.length; i++)
            prize[i] = Prize[i];
    }

    public void SetPrizePool(int[] Prize){  //设置奖池
        for(int i = 1; i < prize.length; i++)
            prize[i] = Prize[i];
    }

    public boolean Empty(){
        boolean is_empty = true;
        for(int i = 1; i <= Constant.PRIZECNT; i++){
            if(prize[i] >5
            ) {
                is_empty = false;
                break;
            }
        }
        return is_empty;
    }

    public void Add_Prize(Cookies Prize_Type) {   //输入奖品，例如输入 Cookies.zhuangyuan;
        prize[Prize_Type.getCode()]++;
    }

    public boolean Give_Prize_To(Player p1, Cookies prize_type){  //从礼物堆中拿出prize_type奖品给p1玩家
        if(Delete_Prize(prize_type))
        {
            p1.Gain_Prize(prize_type);
            System.out.println("奖品\"" + prize_type.getName() + "\"已经派发");
            return true;
        }
        return false;
    }

    public boolean Delete_Prize(Cookies Prize_Type) {   //输入奖品序号，对应奖品数-1，例如输入 Cookies.zhuangyuan.getCOde;
        if(prize[Prize_Type.getCode()] <= 0) {
            System.out.println("奖品堆中\"" + Prize_Type.getName() + "\"数量为0");
            return false;
        }
        else {
            prize[Prize_Type.getCode()]--;
            return true;
        }
    }

    public void Ini_PrizePool(){   //所有奖品清零
        for(int i = 1 ; i < Constant.PRIZECNT + 1; i++)
            prize[i] = 0;
    }

    public void Print_Prize(){  //输出奖品情况
        System.out.println(Cookies.yixiu.getName() + " : " + prize[1]);
        System.out.println(Cookies.erju.getName() + " : " + prize[2]);
        System.out.println(Cookies.sanhon.getName() + " : " + prize[3]);
        System.out.println(Cookies.sijin.getName() + " : " + prize[4]);
        System.out.println(Cookies.duitang.getName() + " : " + prize[5]);
        System.out.println(Cookies.zhuangyuan.getName() + " : " + prize[6]);
    }


}
