import test.NullPath;

public class Main2 {
    public static void main(String[] args) {
        int size = 5;
        long start = System.currentTimeMillis();
   
   
        NullPath n2 = new NullPath(size);
        
        long end = System.currentTimeMillis();
        System.out.println("Tiempo transcurrido: " + (end - start) + " ms");
         start = System.currentTimeMillis();
       
        System.out.println("Running NullPathBB with matrix size: " + size);
        NullPathBB solver = new NullPathBB(size);
        end = System.currentTimeMillis();
        System.out.println("Tiempo transcurrido 2: " + (end - start) + " ms");
        
    }
}
