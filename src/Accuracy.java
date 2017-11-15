import java.util.ArrayList;

public class Accuracy {
    private TreeNode root;
    private String fileName="";
    private Double[][] trainData;
    private String[] attributes;
    public Accuracy(TreeNode root, String fileName){
        this.fileName=fileName;
        this.root=root;
        start();

    }
    private void start(){
        DataReader dr=new DataReader(fileName);
        trainData=dr.getDataSet();
        attributes=dr.getAttributes();
        verify(trainData,attributes);
    }
    private void verify(Double[][] trainData,String[] attributes){
        double correct=0;

        for(int i=0;i<trainData.length;i++){
            String verify=traverse(trainData[i],this.root);
            double v=-1;
            if(verify.equals("True")){
                v=1;
            }else{
                v=0;
            }
            if(v==trainData[i][trainData[i].length-1]){
                correct++;
            }
        }
        System.out.println("Correct numbers: "+correct);
        System.out.println(trainData.length);
        System.out.println(correct/trainData.length);
        System.out.println("Accuracy rate: "+(double)(correct/trainData.length));
    }

    private String traverse(Double[] trainData,TreeNode node){
        if(node.getRightNode()==null&&node.getLeftNode()==null){
            return node.getClassLabel();
        }else if(trainData[node.getAttributeIndex()]<node.getCondition()){
            return traverse(trainData, node.getLeftNode());
        }else{
            return traverse(trainData, node.getRightNode());
        }
    }
}
