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
       // root.setApproved(pos);
       // root.setDennied(neg);
        //root.setClassLabel();
        for(int j=0;j<dr.getAttributes().length;j++){
            aIndice.add(j);
            j++;
        }
        new BuildTree(dataset,rowIndice,dr.getAttributes(),root);
        root.traverse(1);
        System.out.println("############Verify Start########");
        System.out.println("################Build Tree by all training data#################");
        Accuracy accu=new Accuracy(root,"gene_expression_test.csv");
        Double[][] trainData70=dr.getTrainData();
        ArrayList<Integer> rowIndice70=new ArrayList<>();
        for(int i=0;i<trainData70.length;i++){
            rowIndice70.add(i);
        }
        TreeNode root70=new TreeNode();
        new BuildTree(trainData70,rowIndice70,dr.getAttributes(),root70);
        root.traverse(1);
        System.out.println("############Verify Start########");
        System.out.println("################Build Tree by 70% training data#################");
        accu=new Accuracy(root70,"gene_expression_test.csv");
        System.out.println("#############Validate tree##################");


        validateTree vt=new validateTree(root70,dr.getValidateData(),dr.getAttributes());
        vt.start();
        System.out.println("############DotFile Writing########");
        DotFile df=new DotFile(root70,"origin.dot");

        System.out.println("#################Pruning start#############################");
        PostPrune pp=new PostPrune(root70,true);
        root70.traverse(1);
        Accuracy accu2=new Accuracy(root70,"gene_expression_test.csv");
    }
}
