data:extend({
    {
        type = "item",
        name = "FMB_ITEM",
        icon = "__FMB_NAME__/graphics/icons/FMB_SUBGROUP/FMB_ITEM.png",
        flags = {"goes-to-quickbar"},
        subgroup = "FMB_SUBGROUP",
        order = "z",
        stack_size = 200
    },
    {
        type = "recipe",
        name = "FMB_ITEM",
	    enabled = true,
        ingredients = 
	    {
	    	FMB_INGREDIENTS
	    },
        result = "FMB_ITEM"
    }
})
