import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.BiConsumer;

public class Main {

    public static void main(String args[]){
        String fileNmae="gene_expression_training.csv";
        DataReader dr = new DataReader(fileNmae);
        TreeNode root=new TreeNode();
        ArrayList<Integer> rowIndice=new ArrayList<>();
        HashSet<Integer> aIndice=new HashSet<>();
        Double[][] dataset=dr.getDataSet();
        int pos=0;
        int neg=0;
        for(int i=0;i<dataset.length;i++){
            rowIndice.add(i);
            if(dataset[i][dataset[i].length-1]==0){
                neg++;
            }else{
                pos++;
            }
        }
        root.setApproved(pos);
        root.setDennied(neg);
        root.setClassLabel();
        for(int j=0;j<dr.getAttributes().length;j++){
            aIndice.add(j);
            j++;
        }
        new BuildTree(dataset,rowIndice,dr.getAttributes(),root);
        root.traverse(1);
        System.out.println("############Verify Start########");
        Accuracy accu=new Accuracy(root,"gene_expression_test.csv");


//        System.out.println("#################Pruning start#############################");
//        PostPrune pp=new PostPrune(root);
//        root.traverse(1);

    }
}
