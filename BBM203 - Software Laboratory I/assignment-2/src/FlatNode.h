//
// Created by muham on 19.11.2022.
//

#ifndef ASS203_2_FLATNODE_H
#define ASS203_2_FLATNODE_H


class FlatNode {
private:
    FlatNode * next = nullptr;
    FlatNode * prev = nullptr;
    bool isEmpty = false;
public:
    bool getIsEmpty() const;

private:
    // CHECK IF DEFAULT VALUE IS TRUE
    int bandWidth;
    int id;
public:
    FlatNode(int initialBandWidth, int id);
    void addAfter(FlatNode * node);
    void addBefore(FlatNode * node);
    /**
     * removes node connections without deallocate.
     */
    void extract();

    int getBandWidth() const;

    void setBandWidth(int newBandWidth);

    FlatNode *getNext() const;

    void setNext(FlatNode *newNext);

    FlatNode *getPrev() const;

    void setPrev(FlatNode *newPrev);

    int getId() const;

    std::string toString() const;
};


#endif //ASS203_2_FLATNODE_H
