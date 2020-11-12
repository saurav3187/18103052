public class q2 extends Thread
{
	int start;
    static int maxDiv = 0, number = 0;
    public q2(int start)
    {
        this.start = start;
    }

    public static void main(String[] args)
    {
        q2 t1 = new q2(1), t2 = new q2(10001), t3 = new q2(20001);
        q2 t4 = new q2(30001), t5 = new q2(40001);
        q2 t6 = new q2(50001), t7 = new q2(60001);
        q2 t8 = new q2(70001), t9 = new q2(80001);
        q2 t10 = new q2(90001);
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        try{
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        t9.join();
        t10.join();
        }catch(Exception e){}
        long end = System.currentTimeMillis();
        System.out.println(number+" has maximum "+maxDiv+" no of divisors.");
        System.out.println("Total time elapsed: "+(end-start));
    }

    public void run()
    {
        int divisor=0;
        for(int i=start; i<this.start+10000; i++)
        {
            divisor=0;
            for(int j=1; j<=i; j++)
            {
                if(i%j==0)
                {
                    divisor+=1;
                }
            }
            if(divisor>maxDiv)
            {
                synchronized(this)
                {
                    maxDiv = divisor;
                    number = i;
                }
            }
        }
    }
}