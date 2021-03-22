import java.util.Scanner;

public class Driver {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        VirtualCmd virtualcmd = new VirtualCmd();
        while(true)
        {
            System.out.print("R:"+virtualcmd.getPath()+">");
            String[] cmd = sc.nextLine().split(" ");
            if(cmd.length == 2)
                virtualcmd.run(cmd[0], cmd[1]);
            else
                virtualcmd.run(cmd[0], null);

        }
    }
}