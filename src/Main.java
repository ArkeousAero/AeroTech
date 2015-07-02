public class Main
{
    public static String mod = "AeroTech";
    public static String version = "0.0.0";
    public static String author = "ArkeousAero";
    public static String homepage = "https://github.com/ArkeousAero/AeroTech";
    public static String description = "Things and things to automate those things.";

    public static String data = "";
    public static String locale_item_names = "[item-name]\n";
    public static String locale_item_group_names = "[item-group-name]\n";

    public static void main(String[] args)
    {
        createDirectory(mod+"_"+version);
        createDirectory(mod+"_"+version+"/prototypes");
        createDirectory(mod+"_"+version+"/prototypes/item-group");
        createDirectory(mod+"_"+version+"/graphics");
        createDirectory(mod+"_"+version+"/locale");
        createDirectory(mod+"_"+version+"/locale/en");

        System.out.print("Creating info file...");
        String info = FileUtils.loadFile("templates/info.json");
        info = info.replaceAll("FMB_NAME",mod);
        info = info.replaceAll("FMB_AUTHOR",author);
        info = info.replaceAll("FMB_VERSION",version);
        info = info.replaceAll("FMB_HOMEPAGE",homepage);
        info = info.replaceAll("FMB_DESCRIPTION",description);
        FileUtils.saveFile(mod+"_"+version+"/info.json",info);
        System.out.println("done!");

        System.out.print("Creating group file...");
        locale_item_group_names += mod.toLowerCase()+"="+mod;
        Group group = new Group(mod.toLowerCase());
        group.addSubgroup(mod.toLowerCase()+"-ore");
        group.addSubgroup(mod.toLowerCase()+"-plate");
        group.addSubgroup(mod.toLowerCase()+"-rod");
        group.addSubgroup(mod.toLowerCase()+"-gear");
        FileUtils.saveFile(mod+"_"+version+"/prototypes/item-group/"+mod.toLowerCase()+".lua",group.toString());
        data += "require(\"prototypes.item-group."+mod.toLowerCase()+"\")\n";
        System.out.println("done!");

        System.out.println("Creating rods...");
        FileUtils.mkdir(mod+"_"+version+"/prototypes/"+mod.toLowerCase()+"-rod");
        createPart("iron","rod","plate");
        createPart("copper","rod","plate");
        System.out.println("done!");

        System.out.println("Creating gears...");
        FileUtils.mkdir(mod+"_"+version+"/prototypes/"+mod.toLowerCase()+"-gear");
        createPart("iron","gear","plate");
        createPart("copper","gear","plate");
        System.out.println("done!");

        System.out.print("Writing data file...");
        FileUtils.saveFile(mod+"_"+version+"/data.lua",data);
        System.out.println("done!");

        System.out.print("Writing locale files...");
        FileUtils.saveFile(mod+"_"+version+"/locale/en/item-names.cfg",locale_item_names);
        FileUtils.saveFile(mod+"_"+version+"/locale/en/item-group-names.cfg",locale_item_group_names);
        System.out.println("done!");
    }

    public static void createDirectory(String directory)
    {
        System.out.print("Creating "+directory+" directory...");
        FileUtils.mkdir(directory);
        System.out.println("done!");
    }

    public static void createPart(String material, String part, String part_required)
    {
        String material_upper = material.substring(0,1).toUpperCase()+material.substring(1);
        String part_upper = part.substring(0,1).toUpperCase()+part.substring(1);
        String item = material+"-"+part;
        String item_upper = material_upper+" "+part_upper;
        String ingredient = material+"-"+part_required;
        String group = mod.toLowerCase()+"-"+part;
        System.out.print("    Creating "+item+"...");
        locale_item_names += item+"="+item_upper+"\n";
        String text = FileUtils.loadFile("templates/item.lua");
        text = text.replaceAll("FMB_NAME",mod)
                .replaceAll("FMB_SUBGROUP",group)
                .replaceAll("FMB_ITEM",item)
                .replaceAll("FMB_INGREDIENTS","{\""+ingredient+"\",1}");
        FileUtils.saveFile(mod+"_"+version+"/prototypes/"+group+"/"+item+".lua",text);
        data += "require(\"prototypes."+group+"."+item+"\")\n";
        System.out.println("done!");
    }
}
