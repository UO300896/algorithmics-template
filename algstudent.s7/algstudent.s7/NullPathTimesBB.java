import test.NullPath;

public class NullPathTimesBB {
    public static void main(String[] args) {
        int size = 10;
        long totalTime = 0;
        long start = 0;
        long end  = 0;
        System.out.println("Running NullPathBB with matrix size: " + size);
        for(int i =0; i < 5; i++) {
        	
        start = System.currentTimeMillis();
   
        NullPathBB solver = new NullPathBB(size);
        end = System.currentTimeMillis();
        totalTime += (end - start);
        System.out.println("Total time : " + totalTime + " ms");
        }
        
        System.out.println("Total time (mean): " + totalTime/5 + " ms");
    }
}
