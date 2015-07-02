import java.util.ArrayList;

public class Group
{
    public String name;
    public ArrayList<String> subgroups;
    public Group(String name_)
    {
        name = name_;
        subgroups = new ArrayList<String>();
    }
    public void addSubgroup(String sub)
    {
        subgroups.add(sub);
    }
    public String toString()
    {
        String result = FileUtils.loadFile("templates/group.lua")
                        .replaceAll("FMB_NAME",Main.mod)
                        .replaceAll("FMB_GROUP",name);
        String subgroups_str = "";
        String subgroup_template = FileUtils.loadFile("templates/subgroup.lua");
        for(int i = 0; i < subgroups.size(); ++i)
        {
            subgroups_str += subgroup_template
                            .replaceAll("FMB_GROUP",name)
                            .replaceAll("FMB_SUBGROUP",subgroups.get(i))
                            .replaceAll("FMB_ORDER",""+(i+1));
        }
        result = result.replaceAll("FMB_SUBGROUPS",subgroups_str);
        return result;
    }
}
