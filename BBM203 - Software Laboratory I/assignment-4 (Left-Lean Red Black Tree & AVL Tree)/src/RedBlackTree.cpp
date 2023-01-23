//
// Created by muham on 20.12.2022.
//

#include <queue>
#include "RedBlackTree.h"

// Constructors:
RedBlackTree::RBTNode::RBTNode(const std::string &name, int price) : name(name), price(price) {}
//--

// Public Methods:
void RedBlackTree::put(const std::string &name, int price) {
    root = put(root, name, price);
    root->color = BLACK;
}

void RedBlackTree::remove(const std::string &name) {
    if (!isRed(root->left) && !isRed(root->right))
        root->color = RED;
    root = remove(root, name);
    if (root) root->color = BLACK;
}

std::string RedBlackTree::toStringTree() {
    // TODO check memory allocation

    // keep same level of nodes in the same vector.

    std::string str;

    std::vector<RBTNode *> currentLevel;
    std::vector<RBTNode *> nextLevel;

    // add root:
    if (root) currentLevel.push_back(root);

    while(!currentLevel.empty()) {
        str += "\t";
        for (auto node: currentLevel) {
            if(node->left) nextLevel.push_back(node->left);
            if (node->right) nextLevel.push_back(node->right);
            str += toString(node) + ",";
        }

        str.pop_back();
        str += "\n";

        currentLevel = nextLevel;
        nextLevel.clear();
    }
    return str;
}

std::string RedBlackTree::toStringItem(const std::string &name) {
    RBTNode *node = searchNode(root, name);
    if (node) return toString(node);
    return "";
}
// --

// ------------------------- Helper Methods --------------------------------
bool RedBlackTree::isRed(RedBlackTree::RBTNode *node) {
    if(!node)
        return false;

    return node->color;
}

RedBlackTree::RBTNode *RedBlackTree::put(RedBlackTree::RBTNode *node, const std::string &name, int price) {
    if (!node)
        return new RBTNode(name, price);

    if (name == node->name)
        node->price = price;
    else if(name > node->name)
        node->right = put(node->right, name, price);
    else if(name < node->name)
        node->left = put(node->left, name, price);

    node = balance(node);

    return node;
}

RedBlackTree::RBTNode *RedBlackTree::balance(RedBlackTree::RBTNode *node) {

    if (isRed(node->right) && !isRed(node->left))
        node = rotateLeft(node);

    if (isRed(node->left) && isRed(node->left->left))
        node = rotateRight(node);

    if (isRed(node->left) && isRed(node->right))
        flipColors(node);

    return node;
}

void RedBlackTree::flipColors(RedBlackTree::RBTNode *node) {
    node->color = !node->color;
    node->left->color = !node->left->color;
    node->right->color = !node->right->color;
}

RedBlackTree::RBTNode *RedBlackTree::rotateLeft(RedBlackTree::RBTNode *node) {
    RBTNode* r = node->right;
    node->right = r->left;
    r->left = node;
    r->color = node->color;
    node->color = RED;
    return r;
}

RedBlackTree::RBTNode *RedBlackTree::rotateRight(RedBlackTree::RBTNode *node) {
    RBTNode* l = node->left;
    node->left = l->right;
    l->right = node;
    l->color = node->color;
    node->color = RED;
    return l;
}

RedBlackTree::RBTNode *RedBlackTree::moveRedLeft(RedBlackTree::RBTNode *node) {
    flipColors(node);
    if (isRed(node->right->left)) {
        node->right = rotateRight(node->right);
        node = rotateLeft(node);
        flipColors(node);
    }
    return node;
}

RedBlackTree::RBTNode *RedBlackTree::remove(RedBlackTree::RBTNode *node, const std::string &name) {
    if (name.compare(node->name) < 0)  {
        if (!isRed(node->left) && !isRed(node->left->left))
            node = moveRedLeft(node);
        node->left = remove(node->left, name);
    }
    else {
        if (isRed(node->left))
            node = rotateRight(node);
        if (name == node->name && (node->right == nullptr)){
            delete node;
            return nullptr;
        }

        if (!isRed(node->right) && !isRed(node->right->left))
            node = moveRedRight(node);
        if (name == node->name) {
            RBTNode* x = min(node->right);
            node->name = x->name;
            node->price = x->price;
            node->right = deleteMin(node->right);
            delete x;
        }
        else node->right = remove(node->right, name);
    }
    return balance(node);
}

RedBlackTree::RBTNode *RedBlackTree::min(RedBlackTree::RBTNode *node) {
    if(!node->left) return node;
    return min(node->left);
}

RedBlackTree::RBTNode *RedBlackTree::moveRedRight(RedBlackTree::RBTNode *node) {
    flipColors(node);
    if(isRed(node->left->left)) {
        node = rotateRight(node);
        flipColors(node);
    }
    return node;
}

RedBlackTree::RBTNode *RedBlackTree::deleteMin(RedBlackTree::RBTNode *node) {
    if(!node->left) return nullptr;

    if(isRed(node->left) && !isRed(node->left->left))
        node = moveRedLeft(node);

    node->left = deleteMin(node->left);
    return balance(node);
}

RedBlackTree::RBTNode *RedBlackTree::searchNode(RedBlackTree::RBTNode *node, const std::string &name) {
    if (!node) return nullptr;

    if (name > node->name)
        return searchNode(node->right, name);

    if (name < node->name)
        return searchNode(node->left, name);

    if (name == node->name)
        return node;

    return nullptr;
}

std::string RedBlackTree::toString(RedBlackTree::RBTNode *node) {
    return "\"" + node->name + "\":\"" + std::to_string(node->price) + "\"";
}

bool RedBlackTree::isEmpty() {
    return root == nullptr;
}

void RedBlackTree::deallocate(RedBlackTree::RBTNode *node) {

    if(!node) return;

    deallocate(node->left);
    deallocate(node->right);

    node->left = nullptr;
    node->right = nullptr;

    delete node;
}

RedBlackTree::~RedBlackTree() {
    deallocate(root);
}

// ------------------------------------------------------------------------




