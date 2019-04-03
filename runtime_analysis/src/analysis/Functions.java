package analysis;

public class Functions
{
    public void foo1(int n)
    {
        for (int i = 0; i < n / 2; i++)
        {
            System.out.println(i);
        }
    }

    public void foo2(int n)
    {
        for (int i = 1; i <= n; i++)
        {
            System.out.println("Hello");
        }

        for (int i = 1; i <= n; i++)
        {
            System.out.println("World!");
        }
    }

    public int foo3(int n)
    {
        int count = 0;
        for (int i = 1; i <= n; i += 2)
        {
            count++;
        }
        return count;
    }

    public int foo4(int n)
    {
        int sum = 0;
        for (int i = 0; i < n; i *= 2)
        {
            sum += 1;
        }
        return sum;
    }

    public void foo5(int[] input)
    {
        if (input.length < 100)
        {
            return;
        }

        for (int i = 0; i < 100; i++)
        {
            System.out.println(input[i]);
        }
    }

    public void foo6(int n)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                System.out.println(i + j);
            }
        }
    }

    public void foo7(int n)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = n; j >= 1; j /= 2)
            {
                System.out.println(j);
            }
        }
    }

    public void foo8(int n)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                for (int k = 0; k < 7; k++)
                {
                    for (int l = 0; l < 9; l++)
                    {
                        System.out.println(i + j + k + l);
                    }
                }
            }
        }
    }

    public void foo9(int[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            for (int j = 1; j <= 1000; j++)
            {
                System.out.println(j + ": " + input[i]);
            }
        }

        for (int i = 1; i < 1000000; i++)
        {
            System.out.println(input[i]);
        }
    }

    public void foo10(int[] input)
    {
        for (int i = 0; i < input.length; i++)
        {
            System.out.println(input[i]);
        }

        for (int i = 0; i < input.length; i++)
        {
            for (int j = 0; j < input.length; j++)
            {
                System.out.println(input[i] * input[j]);
            }
        }
    }
}
