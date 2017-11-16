import java.util.ArrayList;

public class PostPrune {

    private TreeNode root;
    private ArrayList<TreeNode> nodeOrder=new ArrayList<>();

    public PostPrune(TreeNode root,boolean pe){
        this.root=root;
        postOrderNode(this.root);
        if(pe) {
            processPE();

        }else{
            process();
        }
    }
    private void process(){
        for(TreeNode node:nodeOrder){
            prune(node);
        }

    }
    private void processPE(){
        for(TreeNode node:nodeOrder){
            pessisPrune(node);
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
    private void prune(TreeNode node){
        double childcorrect=childCorrect(node);
        double childAccuracy=childcorrect/(node.getApproved()+node.getDennied());
        double accuracy=0;
        //accuracy=node.getApproved()/(node.getApproved()+node.getDennied());
        if(node.getClassLabel().equals("True")){
            accuracy=node.getApproved()/(node.getApproved()+node.getDennied());
        }else{
            accuracy=node.getDennied()/(node.getApproved()+node.getDennied());
        }
        if(accuracy>=childAccuracy){
            eliminate(node);
        }
    }
    private void pessisPrune(TreeNode node){
        double childError=childError(node);
        double error=pessimError(node);
        if(error<=childError){
            eliminate(node);
        }
    }
    private double childCorrect(TreeNode node){
        if(node.getLeftNode()==null&&node.getRightNode()==null){
            if(node.getClassLabel().equals("True")){
                return node.getApproved();
            }else{
                return node.getDennied();
            }
//            return node.getApproved();
        }else{
            return childCorrect(node.getRightNode())+childCorrect(node.getLeftNode());
        }

    }
    private void eliminate(TreeNode node){
        node.setRightNode(null);
        node.setLeftNode(null);
    }
    private double childError(TreeNode node){
        if(node.getLeftNode()==null&&node.getRightNode()==null){
            return pessimError(node);
        }else{
            return childError(node.getRightNode())+childError(node.getLeftNode());
        }

    }
    private double pessimError(TreeNode node){
        double z=0.674;
        double e=0;
        double n=node.getApproved()+node.getDennied();
        if(node.getApproved()>=node.getDennied()){
            e=node.getDennied()/n;
        }else{
            e=node.getApproved()/n;
        }
        double p=(e+(Math.pow(z,2)/n)+(z*Math.sqrt((e/n)-(Math.pow(e,2)/n)+(Math.pow(z,2)/4*Math.pow(n,2)))))/(1+(Math.pow(z,2)/n));
        return p;
    }
}
