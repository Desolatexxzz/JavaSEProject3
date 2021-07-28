package service;

import domain.*;

public class TeamService {
    private int counter = 1;//用来为开发团队新增成员自动生成团队中的唯一ID，即memberId。
    private final int MAX_MEMBER = 5;//表示开发团队最大成员数
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;//当前团队总人数
    //获得团队成员数组
    public Programmer[] getTeam(){
        Programmer[] realTeam = new Programmer[total];
        for (int i = 0;i < total;i++){
            realTeam[i] = team[i];
        }
        return realTeam;
    }
    //向团队中添加成员，会抛出各种异常
    public void addMember(Employee e) throws TeamException{
            if (NameListService.chack(e.getId())){
                if (e.getId() == 1){
                    throw new TeamException("该成员不是开发人员，无法添加");
                }else {
                    if (total >= MAX_MEMBER){
                        throw new TeamException("团队人员已满，无法添加");
                    }else {
                        Programmer programmer = (Programmer) e;
                        if (chack(e)){
                            throw new TeamException("该员工已在本开发团队中");
                        }
                        if (programmer.getStatus() == Status.BUSY){
                            throw new TeamException("该员工已是某团队成员");
                        }
                        if (programmer.getStatus() == Status.VOCATION){
                            throw new TeamException("该员工正在休假，无法添加");
                        }
                        if (e.getClass().equals(Architect.class)){
                            if (getNumberOfArchitect()){
                                throw new TeamException("团队中至多只能有一名架构师");
                            }
                        }
                        if (e.getClass().equals(Designer.class)){
                            if(getNumberOfDesigner()){
                                throw new TeamException("团队中至多只能有两名设计师");
                            }
                        }
                        if (e.getClass().equals(Programmer.class)){
                            if (getNumberOfProgrammer()){
                                throw new TeamException("团队中至多只能有三名程序员");
                            }
                        }
                        ((Programmer) e).setMemberId(counter++);
                        ((Programmer) e).setStatus(Status.BUSY);
                        team[total] = (Programmer) e;
                        total++;
                    }
                }

            }else {
                throw new TeamException("公司中没有找到该人");
            }
    }
    //检查某个人是否已经在团队中
    public Boolean chack(Employee e){
        for (int i  = 0; i < total;i++){
            if (team[i].getId() == e.getId()){
                return true;
            }
        }
        return false;
    }
    //返回当前团队中架构师的数量是否已满
    public Boolean getNumberOfArchitect(){
        int count = 0;
        for (int i = 0;i < total;i++){
            if (team[i].getClass().equals(Architect.class)){
                count++;
            }
        }
        if (count >= 1){
            return true;
        }
        return false;
    }
    //返回当前团队中设计师的数量是否已满
    public Boolean getNumberOfDesigner(){
        int count = 0;
        for (int i = 0;i < total;i++){
            if (team[i].getClass().equals(Designer.class)){
                count++;
            }
        }
        if (count >= 2){
            return true;
        }
        return false;
    }
    //返回当前团队中程序员的数量是否已满
    public Boolean getNumberOfProgrammer(){
        int count = 0;
        for (int i = 0;i < total;i++){
            if (team[i].getClass().equals(Programmer.class)){
                count++;
            }
        }
        if (count >= 3){
            return true;
        }
        return false;
    }
    //删除团队中的某个成员，输入memberID(TID)
    public void removeMember(int memberID) throws TeamException{
        for (int i = 0;i < total;i++){
                if (team[i].getMemberId() == memberID){
                    if (i != total - 1){
                        for (int j = i;j < total - 1;j++){
                            team[j] = team[j + 1];
                        }
                        team[total- 1] = null;
                        total--;
                        return;
                    }else {
                        team[i] = null;
                        total--;
                        return;
                    }

                }
        }
        throw new TeamException("没有找到该员工，删除失败");
    }

    public int getTotal() {
        return total;
    }
}
