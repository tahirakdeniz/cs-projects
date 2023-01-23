//
// Created by muham on 20.12.2022.
//

#ifndef INC_203_ASS4_AVLTREE_H
#define INC_203_ASS4_AVLTREE_H


#include "SecondaryTree.h"

class AVLTree : public SecondaryTree{
    struct AVLNode {
        AVLNode * right = nullptr;
        AVLNode * left = nullptr;

        // key of the node
        std::string name;

        // data of the node
        int price;

        int height = 1;

        AVLNode(const std::string &name, int price);
    };

    AVLNode *root = nullptr;
public:

    void put(const std::string &name, int price) override;

    void remove(const std::string &name) override;


    std::string toStringTree() override;
    std::string toStringItem(const std::string &name) override;
    bool isEmpty() override;

    ~AVLTree() override;

private:
    static AVLNode *leftRotate(AVLNode *node);
    static AVLNode *rightRotate(AVLNode *node);
    static int height(AVLNode *node);
    static int getBalance(AVLNode *node);
    static AVLNode *put(const std::string &name, int price, AVLNode *node);
    static AVLNode *remove(const std::string &name, AVLNode *node);
    static AVLNode *mostLeft(AVLNode *node);
    static std::string toString(AVLNode *node);
    static AVLNode *searchNode(AVLTree::AVLNode *node, const std::string &name);
    static void deallocate(AVLNode *node);
};


#endif //INC_203_ASS4_AVLTREE_H
