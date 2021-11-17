import java.util.Scanner;
import java.math.BigInteger;

class rsa {

    static int gcd(int a, int h) {
        int temp;
        while (true) {
            temp = a % h;
            if (temp == 0)
                return h;
            a = h;
            h = temp;
        }
    }

    static int findD(int phi, int e) {

        int i;
        for (i = 1; i < phi; i++) {
            if ((i * e) % phi == 1) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int p, q;
        System.out.print("Enter Value of P = ");
        p = sc.nextInt();

        System.out.print("Enter Value of Q = ");
        q = sc.nextInt();

        // Calculating N
        int n;
        n = p * q;
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("Value of N = (P * Q) => " + n);

        int Phi_n;
        Phi_n = (p - 1) * (q - 1);
        System.out.println("Value of Phi of N = (P-1) * (Q-1) => " + Phi_n);

        // finding E
        int e = 2;
        while (e < Phi_n) {
            if (gcd(e, Phi_n) == 1)
                break;
            else
                e++;
        }
        System.out.println("value of E  = " + e);

        // finding D
        int d;
        d = findD(Phi_n, e);

        System.out.println("Value of D = " + d);
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        // public key = {e,n}
        // private key = {d,n}

        System.out.println("\tYour Public key = { " + e + " , " + n + " } ");
        System.out.println("\tYour Private key = { " + d + " , " + n + " } ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.print("Enter a Character = ");
        char P = sc.next().charAt(0);

        int K = P;

        // Cyphertext = P^e mod n

        BigInteger BI_K = new BigInteger(String.valueOf(K));

        BigInteger BI_N = new BigInteger(String.valueOf(n));

        BigInteger CT = (BI_K.pow(e)).mod(BI_N);

        // plain Text = C^d mod n
        // convert Cypher Text to plain text

        BigInteger PT = (CT.pow(d)).mod(BI_N);

        int Decrypted_Int = PT.intValue();
        char Decrypted_Char = (char) Decrypted_Int;
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("Plain Text = " + P);
        System.out.println("Cypher Text = " + CT);
        System.out.println("Decrypted Plain Text = " + Decrypted_Char);
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

    }
}