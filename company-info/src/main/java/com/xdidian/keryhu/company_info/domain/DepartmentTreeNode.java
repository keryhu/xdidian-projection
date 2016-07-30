package com.xdidian.keryhu.company_info.domain;

import java.util.*;

import com.xdidian.keryhu.tree.MultiTreeNode;
import com.xdidian.keryhu.tree.TraversalAction;
import com.xdidian.keryhu.tree.TreeNode;
import com.xdidian.keryhu.tree.TreeNodeException;

/**
 * 实现了多维tree的具体tree array应用，array的优点，插入，查询速度快，缺点是，大小固定，不能随意更改，目前设置
 * 的一个节点最大的直接子节点的数目是20。，应该能满足最大的默认需求。
 * 
 * json 序列化的时候，显示哪些fields 
 * 
 *
 */

@SuppressWarnings("hiding")
public class DepartmentTreeNode<Department> extends MultiTreeNode<Department> {

	
	private static final long serialVersionUID = 1L;

	/**
	 * 默认的一个节点拥有的直接的子节点的数目为10
	 */
	private static final int DEFAULT_BRANCHING_FACTOR = 20;

	/**
	 * 最大的array，是integer的最大限制－8
	 */
	
	private static final  int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * 当前节点的子节点的数组对象。
	 */
	private  Object[] subtrees;

	/**
	 * 在当前tree中，子节点的数目
	 * 
	 */
	
	private  int subtreesSize;

	/**
	 * 当前节点拥有的直接子节点的对象。
	 * 
	 */
	private final  int branchingFactor;

	
	public DepartmentTreeNode(Department data) {
	 
		super(data);
		this.branchingFactor = DEFAULT_BRANCHING_FACTOR;
		this.subtrees = new Object[branchingFactor];
	}

	/**
	 * 
	 * 重新设置当前节点和他拥有的子节点的最大容量
	 * 
	 */
	
	public DepartmentTreeNode(Department data, int branchingFactor) {
		super(data);
		if (branchingFactor < 0) {
			throw new IllegalArgumentException("Branching factor can not be negative");
		}
		this.branchingFactor = branchingFactor;
		this.subtrees = new Object[branchingFactor];
	}

	/**
	 * 返回当前节点的子节点和后代
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends TreeNode<Department>> subtrees() {
		if (isLeaf()) {
			return Collections.<TreeNode<Department>>emptySet();
		}
		Collection<TreeNode<Department>> subtrees = new HashSet<>(subtreesSize);
		for (int i = 0; i < subtreesSize; i++) {
			TreeNode<Department> subtree = (TreeNode<Department>) this.subtrees[i];
			subtrees.add(subtree);
		}
		return subtrees;
	}

	/**
	 * 将参数的节点和他的后代加到当前节点中去。
	 * 
	 */
	@Override
	public boolean add(TreeNode<Department> subtree) {
		if (subtree == null) {
			return false;
		}
		linkParent(subtree, this);
		ensureSubtreesCapacity(subtreesSize + 1);
		subtrees[subtreesSize++] = subtree;
		return true;
	}

	/**
	 * 考虑给当前节点，增加子节点的容量
	 */
	
	private void ensureSubtreesCapacity(int minSubtreesCapacity) {
		if (minSubtreesCapacity > subtrees.length) {
			increaseSubtreesCapacity(minSubtreesCapacity);
		}
	}

	/**
	 * 增加当前节点的子节点的容量
	 */
	
	private void increaseSubtreesCapacity(int minSubtreesCapacity) {
		int oldSubtreesCapacity = subtrees.length;
		int newSubtreesCapacity = oldSubtreesCapacity + (oldSubtreesCapacity >> 1);
		if (newSubtreesCapacity < minSubtreesCapacity) {
			newSubtreesCapacity = minSubtreesCapacity;
		}
		if (newSubtreesCapacity > MAX_ARRAY_SIZE) {
			if (minSubtreesCapacity < 0) {
				throw new OutOfMemoryError();
			}
			newSubtreesCapacity = minSubtreesCapacity > MAX_ARRAY_SIZE ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
		}
		subtrees = Arrays.copyOf(subtrees, newSubtreesCapacity);
	}

	/**
	 *删除指定参数的节点和它的后代，从当前tree中，找出第一个满足要求的。
	 */
	
	@Override
	public boolean dropSubtree(TreeNode<Department> subtree) {
		if (subtree == null
				|| isLeaf()
				|| subtree.isRoot()) {
			return false;
		}
		int mSubtreeIndex = indexOf(subtree);
		if (mSubtreeIndex < 0) {
			return false;
		}
		int mNumShift = subtreesSize - mSubtreeIndex - 1;
		if (mNumShift > 0) {
			System.arraycopy(subtrees, mSubtreeIndex + 1, subtrees, mSubtreeIndex, mNumShift);
		}
		subtrees[--subtreesSize] = null;
		unlinkParent(subtree);
		return true;
	}

	/**
	 * 返回指定节点和它的后代，出现当前tree，array中位置的下标，第一个满足要求的就行。
	 *
	 */
	@SuppressWarnings("unchecked")
	private int indexOf(TreeNode<Department> subtree) {
		for (int i = 0; i < subtreesSize; i++) {
			TreeNode<Department> mSubtree = (TreeNode<Department>) subtrees[i];
			if (mSubtree.equals(subtree)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 清除当前节点下面的所有节点和后代
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		if (!isLeaf()) {
			for (int i = 0; i < subtreesSize; i++) {
				TreeNode<Department> subtree = (TreeNode<Department>) subtrees[i];
				unlinkParent(subtree);
			}
			subtrees = new Object[branchingFactor];
			subtreesSize = 0;
		}
	}

	/**
	 * 返回当前tree的 iterator集合
	 */
	@Override
	public TreeNodeIterator iterator() {
		return new TreeNodeIterator() {

			/**
			 * 如果当前节点不是最后的职业，返回最左边的子节点。
			 *
			 */
			@SuppressWarnings("unchecked")
			@Override
			protected TreeNode<Department> leftMostNode() {
				return (TreeNode<Department>) subtrees[0];
			}

			/**
			 * 返回他相邻的右边的兄弟节点，
			 */
			
			@Override
			@SuppressWarnings("unchecked")
			protected TreeNode<Department> rightSiblingNode() {
				DepartmentTreeNode<Department> mParent = (DepartmentTreeNode<Department>) parent;
				int rightSiblingNodeIndex = mParent.indexOf(DepartmentTreeNode.this) + 1;
				return rightSiblingNodeIndex <= mParent.subtreesSize ?
						(TreeNode<Department>) mParent.subtrees[rightSiblingNodeIndex] : null;
			}
		};
	}

	/**
	 * 检测当前节点是否是最终的枝叶。
	 */
	
	@Override
	public boolean isLeaf() {
		return subtreesSize == 0;
	}

	/**
	 *检测当前节点是否拥有指定参数的节点
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean hasSubtree(TreeNode<Department> subtree) {
		if (subtree == null
				|| isLeaf()
				|| subtree.isRoot()) {
			return false;
		}
		for (int i = 0; i < subtreesSize; i++) {
			TreeNode<Department> mSubtree = (TreeNode<Department>) subtrees[i];
			if (subtree.equals(mSubtree)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回当前节点和他的后代，是否拥有指定的节点。
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(TreeNode<Department> node) {
		if (node == null
				|| isLeaf()
				|| node.isRoot()) {
			return false;
		}
		for (int i = 0; i < subtreesSize; i++) {
			TreeNode<Department> subtree = (TreeNode<Department>) subtrees[i];
			if (subtree.equals(node)) {
				return true;
			}
			if (subtree.contains(node)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 从整个tree中，删除满足指定节点的第一个节点。
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(TreeNode<Department> node) {
		if (node == null
				|| isLeaf()
				|| node.isRoot()) {
			return false;
		}
		if (dropSubtree(node)) {
			return true;
		}
		for (int i = 0; i < subtreesSize; i++) {
			TreeNode<Department> subtree = (TreeNode<Department>) subtrees[i];
			if (subtree.remove(node)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 以正向的顺序，预览整个节点。
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void traversePreOrder(TraversalAction<TreeNode<Department>> action) {
		if (!action.isCompleted()) {
			action.perform(this);
			if (!isLeaf()) {
				for (int i = 0; i < subtreesSize; i++) {
					TreeNode<Department> subtree = (TreeNode<Department>) subtrees[i];
					subtree.traversePreOrder(action);
				}
			}
		}
	}

	/**
	 * 以反方向的顺序从当前节点，预览整个tree
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void traversePostOrder(TraversalAction<TreeNode<Department>> action) {
		if (!action.isCompleted()) {
			if (!isLeaf()) {
				for (int i = 0; i < subtreesSize; i++) {
					TreeNode<Department> subtree = (TreeNode<Department>) subtrees[i];
					subtree.traversePostOrder(action);
				}
			}
			action.perform(this);
		}
	}

	/**
	 * 从当前节点到枝叶节点的最大的间隔有几个。
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public int height() {
		if (isLeaf()) {
			return 0;
		}
		int height = 0;
		for (int i = 0; i < subtreesSize; i++) {
			TreeNode<Department> subtree = (TreeNode<Department>) subtrees[i];
			height = Math.max(height, subtree.height());
		}
		return height + 1;
	}

	/**
	 * 将指定节点和它的后代，增加到当前节点来。
	 */
	
	@Override
	public boolean addSubtrees(Collection<? extends MultiTreeNode<Department>> subtrees) {
		if (areAllNulls(subtrees)) {
			return false;
		}
		for (MultiTreeNode<Department> subtree : subtrees) {
			linkParent(subtree, this);
		}
		Object[] subtreesArray = subtrees.toArray();
		int subtreesArrayLength = subtreesArray.length;
		ensureSubtreesCapacity(subtreesSize + subtreesArrayLength);
		System.arraycopy(subtreesArray, 0, this.subtrees, subtreesSize, subtreesArrayLength);
		subtreesSize += subtreesArrayLength;
		return subtreesArrayLength != 0;
	}

	/**
	 * 返回当前节点的兄弟节点。和他的 后代。
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends MultiTreeNode<Department>> siblings() {
		if (isRoot()) {
			String message = String.format("Unable to find the siblings. The tree node %1$s is root", root());
			throw new TreeNodeException(message);
		}
		DepartmentTreeNode<Department> mParent = (DepartmentTreeNode<Department>) parent;
		int parentSubtreesSize = mParent.subtreesSize;
		if (parentSubtreesSize == 1) {
			return Collections.emptySet();
		}
		Object[] parentSubtreeObjects = mParent.subtrees;
		Collection<MultiTreeNode<Department>> siblings = new HashSet<>(parentSubtreesSize - 1);
		for (int i = 0; i < parentSubtreesSize; i++) {
			MultiTreeNode<Department> parentSubtree = (MultiTreeNode<Department>) parentSubtreeObjects[i];
			if (!parentSubtree.equals(this)) {
				siblings.add(parentSubtree);
			}
		}
		return siblings;
	}

 

	
}
