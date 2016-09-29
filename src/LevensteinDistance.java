/**
 * 编辑距离， 又称Levenshtein距离，是指两个字串之间，由一个转成另一个所需的最少编辑操作次数。
 * 该类中许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
 * 
 * 使用动态规划算法。算法复杂度：m*n。
 * 
 * @author yifeng
 *
 */
public class LevensteinDistance implements MetricSpace<String>{
    private double insertCost = 1;       // 可以写成插入的函数，做更精细化处理
    private double deleteCost = 1;       // 可以写成删除的函数，做更精细化处理
    private double substitudeCost = 1; // 可以写成替换的函数，做更精细化处理。比如使用键盘距离。
    
    public double computeDistance(String target,String source){
        int n = target.trim().length();
        int m = source.trim().length();
        
        double[][] distance = new double[n+1][m+1];
        
        distance[0][0] = 0;
        for(int i = 1; i <= m; i++){
            distance[0][i] = i;
        }
        for(int j = 1; j <= n; j++){
            distance[j][0] = j;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <=m; j++){
                double min = distance[i-1][j] + insertCost;
                
                if(target.charAt(i-1) == source.charAt(j-1)){
                    if(min > distance[i-1][j-1]) 
                        min = distance[i-1][j-1];
                }else{
                    if(min > distance[i-1][j-1] + substitudeCost)
                        min = distance[i-1][j-1] + substitudeCost;
                }
                
                if(min > distance[i][j-1] + deleteCost){
                    min = distance[i][j-1] + deleteCost;
                }
                
                distance[i][j] = min;
            }
        }
        
        return distance[n][m];
    }

    @Override
    public double distance(String a, String b) {
        return computeDistance(a,b);
    }

    public static void main(String[] args) {
        LevensteinDistance distance = new LevensteinDistance();
        System.out.println(distance.computeDistance("你好","好你"));
    }
}