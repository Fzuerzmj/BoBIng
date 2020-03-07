package P1;
import java.io.*;
import P1.Cookies;
import P1.PrizePool;
import P1.Constant;
import P1.Dice;
import P1.Player;

import java.util.Scanner;

class Game{
    private int player_cnt;  //玩家人数
    int[] ini_prize_cnt; //初始奖品数量
    private PrizePool TolPrizePool;  //游戏总的礼物池
    private Player[] players;   //玩家列表
    int time_stream;  //时间流记录现在是哪个玩家回合
    Dice dice;
    int ZHUANGYUAN_NUM = 0;  //记录获得状元玩家号，等待所有玩家回合结束重设置为0
    Cookies ZHUANGYUAN_TYPE = Cookies.duitang; //记录上一个状元获得者类型

    Game(){  //游戏初始化，设置玩家人数，奖品数量
        TolPrizePool = new PrizePool();
        dice = new Dice();
        time_stream = 0;
        ini_prize_cnt = new int[Constant.PRIZECNT + 1];
        Scanner Scannner = new Scanner(System.in);

        System.out.println("\n\n设定玩家人数：");
        player_cnt = Scannner.nextInt();

        players = new Player[player_cnt + 1];  //初始化玩家数组
        for(int i = 0;i<=player_cnt;i++)
            players[i] = new Player();
        //初始化奖品数量
        System.out.println("\n\n设定奖品数量：\n");
        for(int i = 1; i <= Constant.PRIZECNT; i++)
        {
            System.out.println("请输入\"" + Cookies.getName(i) + "\"奖项的数量：" );
            ini_prize_cnt[i] = Scannner.nextInt();
        }
        TolPrizePool.SetPrizePool(ini_prize_cnt);

    }

    public Cookies What_Cookies(int[] dice_num)   //判断玩家掷出骰子的得奖情况
    {
        Cookies cookies; //枚举2
        if(dice_num[4] == 6)    cookies = Cookies.liubeihon;
        else if(dice_num[4] == 4 && dice_num[1] == 2)  cookies = Cookies.chajinhua;
        else if(dice_num[1] == 5 ||dice_num[2] == 5 ||dice_num[3] == 5 ||dice_num[4] == 5 ||dice_num[5] == 5 ||dice_num[6] == 5 ) cookies = Cookies.wuzidengke;
        else if(dice_num[4] == 4) cookies = Cookies.duitang;
        else if(dice_num[1] == 1 && dice_num[2] == 1 &&dice_num[3] == 1 &&dice_num[4] == 1 &&dice_num[5] == 1 &&dice_num[6] == 1 ) cookies = Cookies.duitang;
        else if(dice_num[1] == 4 || dice_num[2] == 4 || dice_num[3] == 4 || dice_num[5] == 4 || dice_num[6] == 4 ) cookies = Cookies.sijin;
        else if(dice_num[4] == 3) cookies = Cookies.sanhon;
        else if(dice_num[4] == 2) cookies = Cookies.erju;
        else if(dice_num[4] == 1) cookies = Cookies.yixiu;
        else cookies = null;
        return cookies;
    }

    public void ShowDiceNum(int[] dice_num){
        System.out.println("你的点数为:");
        for(int i = 1; i <= 6; i++)
        {
            while(dice_num[i] != 0)
            {
                System.out.println(i + "  ");
                dice_num[i]--;
            }
        }  //打印骰子情况
    }

    public void Player_Run_Dice (int[] dice_num)throws IOException{  //玩家投掷骰子
        System.out.println("\n请" + ((time_stream ) % player_cnt + 1) + "号玩家投掷骰子(Y):");
        char ch;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            ch = (char) br.read();
            System.out.print(ch);
        } while(ch != 'y' && ch != 'Y');
        dice.Run(6,dice_num);
    }

    public void Give_Prize(Cookies cookies)  //颁奖
    {
        if(cookies == null)   //未获奖
            System.out.println("很遗憾，你没有中奖，继续努力！");
        else if(cookies == Cookies.zhuangyuan || cookies == Cookies.wuzidengke || cookies == Cookies.chajinhua)  //状元签
        {
            System.out.println("恭喜你掷出\"" + cookies.getName() + "\"");
            System.out.println("“状元饼”采取追饼的方式,将在回合末结算奖品");
            if(ZHUANGYUAN_NUM == 0)  //本回合第一个获得3类状元签中的一类
            {
                ZHUANGYUAN_NUM = (time_stream + 1) % player_cnt;  //记录获得状元签玩家序号，方便本回合结束后结账
                ZHUANGYUAN_TYPE = cookies;
            }
            else {
                if(cookies.getCode() > ZHUANGYUAN_TYPE.getCode())  //同一回合，后来者状元比前者大
                {
                    ZHUANGYUAN_NUM = (time_stream + 1) % player_cnt;  //记录获得状元签玩家序号，方便本回合结束后结账
                    ZHUANGYUAN_TYPE = cookies;
                }
            }

        }
        else {
            System.out.println("恭喜你掷出\"" + cookies.getName() + "\"");
            TolPrizePool.Give_Prize_To(players[(time_stream + 1) % player_cnt],cookies);
        }
    }

    public void EndOfRound_Handle()
    {
        System.out.println("回合结束");
        if(ZHUANGYUAN_NUM == 0) System.out.println("本回合没有人获得\"状元饼\"");
        else {
            System.out.println("恭喜" + ZHUANGYUAN_NUM + "号玩家获得本回合“状元饼”");
            TolPrizePool.Give_Prize_To(players[ZHUANGYUAN_NUM], Cookies.zhuangyuan);
        }
        ZHUANGYUAN_NUM = 0;
        ZHUANGYUAN_TYPE = null;
    }

    public void EndOfGame()   //游戏结束汇总
    {
        System.out.println("\n\n游戏结束");
        for(int i = 1; i <= player_cnt; i++)
        {
            System.out.println("玩家" + i + "获得奖品如下：");
            players[i].Show_Gain_Prizes();  //输出所获得的奖品
            System.out.println(" ");
        }
    }



    void Run() throws IOException{  //开始游戏按钮
        int[] dice_num = {0,0,0,0,0,0,0}; //7


        while(!TolPrizePool.Empty()){  //当礼物池子不为空时
            Player_Run_Dice(dice_num);
            Cookies cookies =  What_Cookies(dice_num);;  //中奖类型
            ShowDiceNum(dice_num);
            Give_Prize(cookies);  //颁奖
            if((time_stream + 1)% player_cnt == 0)
                EndOfRound_Handle();  //回合末结算状元
            time_stream++;
    }
    EndOfGame(); //游戏结束
    }
}


public class BeginGame{
    public static void main(String[] args) throws IOException {
        System.out.println("欢迎使用 “博饼小游戏” 程序");
        Game game = new Game();
        game.Run();
    }
}