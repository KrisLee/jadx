package jadx.core.dex.visitors.regions;

import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class TracedRegionVisitor implements IRegionVisitor {

	protected final Deque<IRegion> regionStack = new ArrayDeque<IRegion>();

	@Override
	public final void enterRegion(MethodNode mth, IRegion region) {
		regionStack.push(region);
	}

	@Override
	public final void processBlock(MethodNode mth, IBlock container) {
		IRegion curRegion = regionStack.peek();
		processBlockTraced(mth, container, curRegion);
	}

	public abstract void processBlockTraced(MethodNode mth, IBlock container, IRegion currentRegion);

	@Override
	public final void leaveRegion(MethodNode mth, IRegion region) {
		regionStack.pop();
	}
}
