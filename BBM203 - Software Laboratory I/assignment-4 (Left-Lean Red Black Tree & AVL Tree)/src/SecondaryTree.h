//
// Created by muham on 20.12.2022.
//

#ifndef INC_203_ASS4_SECONDARYTREE_H
#define INC_203_ASS4_SECONDARYTREE_H


#include <string>

class SecondaryTree {
public:
    /**
     * finds the node with the given name and if it exists make its value to the given value, but if it does not exist then create new node.
     * @param name name if the node to find
     * @param price value of the node
     */
    virtual void put(const std::string& name, int price) = 0;

    /**
     * searches node and removes from the tree
     * @param name name of the node to be deleted
     */
    virtual void remove(const std::string& name) = 0;

    /**
     *
     * @return needed output string for all tree
     */
    virtual std::string toStringTree() = 0;

    /**
     *
     * @param name name of the node to search and create oupput for it.
     * @return needed output string for giving name node.
     */
    virtual std::string toStringItem(const std::string& name) = 0;

    /**
     *
     * @return true if the tree is empty, otherwise false
     */
    virtual bool isEmpty() = 0;

    virtual ~SecondaryTree() = default;
};
#endif //INC_203_ASS4_SECONDARYTREE_H
