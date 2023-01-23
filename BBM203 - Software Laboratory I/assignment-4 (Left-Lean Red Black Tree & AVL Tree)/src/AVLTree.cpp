//
// Created by muham on 20.12.2022.
//

#include <vector>
#include "AVLTree.h"

// Constructor:
AVLTree::AVLNode::AVLNode(const std::string &name, int price) : name(name), price(price) {}
// ---

// Public Methods:
void AVLTree::put(const std::string &name, int price) {
    root = put(name, price, root);
}

void AVLTree::remove(const std::string &name) {
    if (root) root = remove(name, root);
}

std::string AVLTree::toStringTree() {
    std::string str;

    std::vector<AVLNode *> currentLevel;
    std::vector<AVLNode *> nextLevel;

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

std::string AVLTree::toStringItem(const std::string &name) {
    AVLNode * node = searchNode(root, name);
    if (node) return toString(node);
    else return "";
}

bool AVLTree::isEmpty() {
    return root == nullptr;
}

// --

// --- HELPER FUNCTIONS --------------------------------
AVLTree::AVLNode *AVLTree::leftRotate(AVLTree::AVLNode *node) {
    AVLNode *r = node->right;
    AVLNode *rl = r->left;

    r->left = node;
    node->right = rl;

    // Update heights
    node->height = std::max(height(node->left), height(node->right)) + 1;
    r->height = std::max(height(r->left), height(r->right)) + 1;


    return r;
}

AVLTree::AVLNode *AVLTree::rightRotate(AVLTree::AVLNode *node) {
    AVLNode *l = node->left;
    AVLNode *lr = l->right;

    l->right = node;
    node->left = lr;

    // update height
    node->height = std::max(height(node->left), height(node->right)) + 1;
    l->height = std::max(height(l->right), height(l->left)) + 1;


    return l;
}

int AVLTree::height(AVLTree::AVLNode *node) {
    if(!node) return 0;
    else return node->height;
}

int AVLTree::getBalance(AVLTree::AVLNode *node) {
    if(!node) return 0;

    return height(node->left) - height(node->right);
}

AVLTree::AVLNode *AVLTree::put(const std::string &name, int price, AVLTree::AVLNode *node) {
    if(!node) return new AVLNode(name, price);

    if(name == node->name)
        node->price = price;
    else if(name > node->name)
        node->right = put(name, price, node->right);
    else if(name < node->name)
        node->left = put(name, price, node->left);

    node->height = std::max(height(node->left), height(node->right)) + 1;

    int balance = getBalance(node);

    // Left-Left Case
    if (balance > 1 && name < node->left->name)
        return rightRotate(node);

    // Right-Right Case
    if (balance < -1 && name > node->right->name)
        return leftRotate(node);

    // Left-Right Case
    if (balance > 1 && name > node->left->name) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    // Right-Left Case
    if (balance < -1 && name < node->right->name) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

AVLTree::AVLNode *AVLTree::remove(const std::string &name, AVLTree::AVLNode *node) {
    if(!node) return node;

    if(name > node->name)
        node->right = remove(name, node->right);
    else if (name < node->name)
        node->left = remove(name, node->left);
    else {
        // Two children
        if(node->right && node->left) {
            AVLNode *inorderSuccessor = mostLeft(node->right);

            node->price = inorderSuccessor->price;
            node->name = inorderSuccessor->name;

            node->right = remove(inorderSuccessor->name, node->right);
        }

        // No Children
        else if(!node->right && !node->left) {
            delete node;
            node = nullptr;

            return node;
        }

        // One Child
        else {
            AVLNode *child = node->right ? node->right : node->left;

            node->name = child->name;
            node->price = child->price;
            node->left = node->right = nullptr;

            delete child;
        }
    }

    // update height

    node->height = std::max(height(node->left), height(node->right)) + 1;

    int balance = getBalance(node);

    // Left-Left Case
    if (balance > 1 && getBalance(node->left) >= 0)
        return rightRotate(node);

    // Left Right Case
    if (balance > 1 && getBalance(node->left) < 0) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    // Right-Right Case
    if (balance < -1 && getBalance(node->right) <= 0)
        return leftRotate(node);

    // Right Left Case
    if (balance < -1 && getBalance(node->right) > 0) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

AVLTree::AVLNode *AVLTree::mostLeft(AVLTree::AVLNode *node) {
    // assume node is not null

    AVLNode *current = node;

    /* loop down to find the leftmost leaf */
    while (current->left)
        current = current->left;

    return current;
}

std::string AVLTree::toString(AVLTree::AVLNode *node) {
    return "\"" + node->name + "\":\"" + std::to_string(node->price) + "\"";
}

AVLTree::AVLNode *AVLTree::searchNode(AVLTree::AVLNode *node, const std::string &name) {
    if (!node) return nullptr;

    if (name > node->name)
        return searchNode(node->right,name);

    if (name < node->name)
        return searchNode(node->left, name);

    if (name == node->name)
        return node;

    return nullptr;
}

void AVLTree::deallocate(AVLTree::AVLNode *node) {
    if(!node) return;

    deallocate(node->left);
    deallocate(node->right);

    node->left = nullptr;
    node->right = nullptr;

    delete node;
}

AVLTree::~AVLTree() {
    deallocate(root);
}
// --