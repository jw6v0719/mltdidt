import java.util.ArrayList;

public class validateTree {
    private Double[][] vDataSet;
    private TreeNode root;
    private String[] attributes;
    private ArrayList<TreeNode> nodeOrder=new ArrayList<>();
    public validateTree(TreeNode root,Double[][] validateDataset, String[] attributes){
        this.vDataSet=validateDataset;
        this.root=root;
        this.attributes=attributes;
    }

    //apply vData to gain approved and denied of leaf nodes
    public void start(){
        verify(vDataSet,attributes);
        postOrderNode(this.root);
        updateNodeInfo();
    }
    private void verify(Double[][] trainData,String[] attributes){
        //double correct=0;

        for(int i=0;i<trainData.length;i++){
            TreeNode leaf=traverse(trainData[i],this.root);
            String verify=leaf.getClassLabel();
            double v=-1;
            if(verify.equals("True")){
                v=1;
            }else{
                v=0;
            }
            if(v==trainData[i][trainData[i].length-1]){
                leaf.approvedInc();
                //leaf.setApproved(leaf.getApproved()+1);
            }else{
                leaf.deniedInc();
                //leaf.setDennied(leaf.getDennied()+1);
            }
        }
    }

    private TreeNode traverse(Double[] trainData,TreeNode node){
        if(node.getRightNode()==null&&node.getLeftNode()==null){
            return node;
        }else if(trainData[node.getAttributeIndex()]<node.getCondition()){
            return traverse(trainData, node.getLeftNode());
        }else{
            return traverse(trainData, node.getRightNode());
        }
    }
    private void updateNodeInfo(){
        for(TreeNode node:nodeOrder){
            update(node);
            node.setClassLabel();
        }
    }

    private void update(TreeNode node){
        node.setApproved(getTotalApproved(node.getLeftNode())+
                getTotalApproved(node.getRightNode()));
        node.setDennied(getTotalDenied(node.getLeftNode())+
                getTotalDenied(node.getRightNode()));

    }

    private double getTotalApproved(TreeNode node){
        if(node.getRightNode()==null&&node.getLeftNode()==null){
            return node.getApproved();
        }else{
            return getTotalApproved(node.getLeftNode())+getTotalApproved(node.getRightNode());
        }

    }

    private double getTotalDenied(TreeNode node){
        if(node.getRightNode()==null&&node.getLeftNode()==null){
            return node.getDennied();
        }else{
            return getTotalDenied(node.getLeftNode())+getTotalDenied(node.getRightNode());
        }

    }

    private void postOrderNode(TreeNode node){
        //Maintain inner node's order with post ordered
        if(node.getLeftNode()!=null){
            postOrderNode(node.getLeftNode());
        }
        if(node.getRightNode()!=null){
            postOrderNode(node.getRightNode());
        }
        if(node.getRightNode()!=null || node.getLeftNode()!=null){
            nodeOrder.add(node);
        }

    }
}
