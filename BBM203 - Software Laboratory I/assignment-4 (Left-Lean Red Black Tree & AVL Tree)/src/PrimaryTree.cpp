//
// Created by muham on 20.12.2022.
//

#include <queue>
#include "PrimaryTree.h"
#include "AVLTree.h"
#include "RedBlackTree.h"

PrimaryTree::PrimaryTree(TreeType type) : type(type) {}

void PrimaryTree::put(const std::string &category, const std::string &name, int price) {
    getCreatedNode(category, root)->secondaryTree->put(name, price);
}

void PrimaryTree::remove(const std::string &category, const std::string &name) {
    PrimaryNode * node = getSearchedNode(category, root);
    if(node)
        node->secondaryTree->remove(name);
}

PrimaryTree::PrimaryNode *PrimaryTree::getCreatedNode(const std::string &category, PrimaryNode *&node) {
    if(!node) {
        node = new PrimaryNode(category, type);
        return node;
    }
    
    if (node->category == category)
        return node;
    
    if(node->category > category)
        return getCreatedNode(category, node->left);
    
    if(node->category < category)
        return getCreatedNode(category, node->right);

    return nullptr;
}

PrimaryTree::PrimaryNode *PrimaryTree::getSearchedNode(const std::string &category, PrimaryTree::PrimaryNode *&node) {
    if(!node) return nullptr;

    if (node->category == category) {
        return node;
    }

    if(node->category > category) {
        return getSearchedNode(category, node->left);
    }

    if(node->category < category) {
        return getSearchedNode(category, node->right);
    }

    return nullptr;
}

std::string PrimaryTree::toStringItem(const std::string &category, const std::string &name) {
    std::string str;
    PrimaryNode* node = getSearchedNode(category, root);
    if(node) {
        std::string nodeString = node->secondaryTree->toStringItem(name);

        str = "{";
        if(!nodeString.empty()) {
            str += "\n\"" + category + "\":\n";
            str += "\t" + nodeString + "\n";
        }
        str += "}\n";
        return str;
    }

    str = "{}\n";
    return str;
}

std::string PrimaryTree::toStringCategory(const std::string &category) {
    std::string str = "{\n";
    PrimaryNode* node = getSearchedNode(category, root);
    str += toStringNode(node);
    str += "}\n";
    return str;
}

std::string PrimaryTree::toStringAll() {
    std::string str = "{\n";

    auto *queue = new std::queue<PrimaryNode*>();
    queue->push(root);

    // level by level, BFS:
    while(!queue->empty()) {
        PrimaryNode* node = queue->front();
        if(node->left) queue->push(node->left);
        if(node->right) queue->push(node->right);

        str += toStringNode(node);
        queue->pop();
    }

    str += "}\n";

    delete queue;
    return str;
}

std::string PrimaryTree::toStringNode(PrimaryTree::PrimaryNode *node) {

    std::string str;
    str = "\"" + node->category + "\":";
    if(node->secondaryTree->isEmpty()) {
        str += "{}\n";
        return str;
    }
    str += "\n" + node->secondaryTree->toStringTree();
    return str;
}

PrimaryTree::~PrimaryTree() {
    deallocate(root);
}

void PrimaryTree::deallocate(PrimaryNode *node) {
    if(!node)
        return;

    deallocate(node->left);
    deallocate(node->right);

    node->left = nullptr;
    node->right = nullptr;

    delete node->secondaryTree;
    delete node;
}

PrimaryTree::PrimaryNode::PrimaryNode(const std::string &category, TreeType type) : category(category) {
    switch (type) {
        case AVL_TREE:
            secondaryTree = new AVLTree();
            break;
        case RED_BLACK_TREE:
            secondaryTree = new RedBlackTree();
            break;
        default:
            break;
    }
}
