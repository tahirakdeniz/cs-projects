//
// Created by muham on 20.12.2022.
//

#ifndef INC_203_ASS4_PRIMARYTREE_H
#define INC_203_ASS4_PRIMARYTREE_H

#include <string>
#include "SecondaryTree.h"


enum TreeType {AVL_TREE, RED_BLACK_TREE};

class PrimaryTree {
    struct PrimaryNode {
        PrimaryNode *left = nullptr;
        PrimaryNode *right = nullptr;
        // the key of the node:
        std::string category;
        // the data of the node:
        SecondaryTree *secondaryTree = nullptr;

        PrimaryNode(const std::string &category, TreeType type);
    };

    const TreeType type;
    PrimaryNode * root = nullptr;
public:
    explicit PrimaryTree(TreeType type);

    virtual ~PrimaryTree();

    /**
     * puts item to tree system.
     * @param category determines item will add to  which secondary tree
     * @param name name of the node of the secondary tree
     * @param price value of the node of the secondary tree
     */
    void put(const std::string& category, const std::string& name, int price) ;

    /**
     * removes item from tree system
     * @param category determines item will add to  which secondary tree
     * @param name name of the node of the secondary tree
     */
    void remove(const std::string& category, const std::string& name);
    std::string toStringAll();
    std::string toStringCategory(const std::string& category);
    std::string toStringItem(const std::string& category, const std::string& name);


private:
    /**
     * finds the node with the given category, if it doesn't exist, creates.
     * @param category string to search category key.
     * @return created or found node
     */
    PrimaryNode* getCreatedNode(const std::string& category, PrimaryNode *&node);

    /**
     * finds the node with the given category, if it doesn't exist do nothing.
     * @param category string to search category key.
     * @return found node, if not found returns nullptr
     */
    PrimaryNode* getSearchedNode(const std::string& category, PrimaryNode *&node);
    static void deallocate(PrimaryNode *node);
    static std::string toStringNode(PrimaryNode* node);
};


#endif //INC_203_ASS4_PRIMARYTREE_H
