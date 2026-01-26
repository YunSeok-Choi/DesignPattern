package factory;

import flyweight.ConiferTree;
import flyweight.DeciduousTree;
import flyweight.Tree;
import flyweight.TreeType;

import java.util.EnumMap;
import java.util.Map;

public class TreeFactory {

    private final Map<TreeType, Tree> treeCache = new EnumMap<>(TreeType.class);

    public Tree getTree(TreeType type) {
        return treeCache.computeIfAbsent(type, this::createTree);
    }

    private Tree createTree(TreeType type) {
        return switch (type) {
            case DECIDUOUS -> new DeciduousTree();
            case CONIFER -> new ConiferTree();
        };
    }
}
