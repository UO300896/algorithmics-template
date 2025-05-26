

public class Main2 {
    public static void main(String[] args) {
        int size = 5;
        long start = System.currentTimeMillis();
   
   
        NullPathBB n2 = new NullPathBB(size);
        
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + " ms");
         start = System.currentTimeMillis();
       
        System.out.println("Running NullPathBB with matrix size: " + size);
        NullPathBB solver = new NullPathBB(size);
        end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + " ms");
        
    }
}
