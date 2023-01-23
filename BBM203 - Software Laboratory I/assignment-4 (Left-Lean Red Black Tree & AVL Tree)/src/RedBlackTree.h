//
// Created by muham on 20.12.2022.
//

#ifndef INC_203_ASS4_REDBLACKTREE_H
#define INC_203_ASS4_REDBLACKTREE_H


#include "SecondaryTree.h"

class RedBlackTree : public SecondaryTree{
private:
    static const bool RED   = true;
    static const bool BLACK = false;

    struct RBTNode {
        RBTNode* left = nullptr;
        RBTNode* right = nullptr;

        // key of the node
        std::string name;

        // data of the node
        int price;

        bool color = RED;

        RBTNode(const std::string &name, int price);
    };

    RBTNode * root = nullptr;
public:
    void put(const std::string &name, int price) override;
    void remove(const std::string &name) override;

    std::string toStringTree() override;

    std::string toStringItem(const std::string &name) override;
    bool isEmpty() override;

    ~RedBlackTree() override;

private:
    static RBTNode* put(RBTNode * node, const std::string &name, int price);
    static RBTNode* balance(RBTNode *node);
    static void flipColors(RBTNode *node);
    static RBTNode* rotateLeft(RBTNode *node);
    static RBTNode* rotateRight(RBTNode *node);
    static bool isRed(RBTNode *node);
    static RBTNode* moveRedLeft(RBTNode *node);
    static RBTNode* moveRedRight(RBTNode *node);
    static RBTNode* remove(RBTNode *node, const std::string &name);
    static RBTNode* min(RBTNode* node);
    static RBTNode* deleteMin(RBTNode* node);
    static RBTNode* searchNode(RBTNode *node, const std::string &name);
    static std::string toString(RBTNode *node);
    static void deallocate(RBTNode *node);
};


#endif //INC_203_ASS4_REDBLACKTREE_H
