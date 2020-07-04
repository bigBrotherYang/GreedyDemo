package com.xktj.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * 题目 ： 现有人民币面额 1 5 10 20 50 100 用户传入金额实现找零功能最合理找零功能
 * 
 * 例如 ：143 
 * 算出来结果 ： 100有1张 50有0张 20有2张 10有0张 5有0张 1有3张
 *
 */
public class GreedyAlgorithm {

	@Test
	public void a() {
		// this.splitChange(173);
		// this.copyMoney(143);
//		   this.copyMoney2(143);
		
		this.greedy(new int[] {12,33,12,3,1}, 3);
		
//		int[][] course = {{2,5},{3,3},{1,8},{1,3}};
         
//		scheduleCourse(course);
	}

	public void splitChange(int money) {
		int[] prices = { 100, 50, 20, 10, 5, 1 };
		int[] notes = new int[prices.length];
		int change = money;
		if (money > 0) {
			while (change > 0) {
				for (int i = 0; i < prices.length; i++) {
					int count = 0;
					for (int k = 0; change - prices[i] >= 0; k++) {
						if (change - prices[i] >= 0) {
							change = change - prices[i];
							count++;
						} else

							break;

					}
					notes[i] = count;
				}
			}
		}
		System.out.println("找零：");
		for (int num = 0; num < prices.length; num++) {
			System.out.print(notes[num] + "张" + prices[num] + "元  ");
		}
	}

	/**
	 * 第一遍
	 */

	public void copyMoney(int money) {
		int[] prices = { 100, 50, 20, 10, 5, 1 };
		int[] notes = new int[prices.length];
		int changeMoeny = money;
		if (money > 0) {
			for (int i = 0; i < prices.length; i++) {
				int count = 0;
				for (; changeMoeny - prices[i] >= 0;) {
					changeMoeny = changeMoeny - prices[i];
					++count;
				}
				notes[i] = count;
			}
		}
		for (int i = 0; i < notes.length; i++) {
			System.out.println(notes[i] + "张" + prices[i] + "元");
		}
	}

	//----------------------第二次--------------------------------------//        
	public void copyMoney2(int money) {
		int[] prices = { 100, 50, 20, 10, 5, 1 };
		int[] arr = new int[prices.length];
		int totalmoney = money;
		for (int i = 0; i < arr.length; i++) {
			int num = 0;
			for(;totalmoney-prices[i]>=0;) {
				totalmoney-=prices[i];
				num++;
			}
			arr[i] = num;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println(prices[i]+"有"+arr[i]+"张");
		}
		
	}
	
	
	
	// -- tow----------------------------------------------------///
	/**
	 * @Description: 多机调度问题
	 * @Date: 15:49 2019/8/10
	 * @Param: [a, m]
	 * @return: int
	 */
	public int greedy(int[] a, int m) {
		// int n = a.length - 1;//a的下标从1开始，所以n（作业的数目）=a.length-1
		int n = a.length;
		int sum = 0;
		if (n <= m) {
			for (int i = 0; i < n; i++)
				sum += a[i];
			System.out.println("为每个作业分别分配一台机器");
			return sum;
		}
		List<JobNode> d = new ArrayList<>();// d保存所有的作业
		for (int i = 0; i < n; i++) {// 将所有的作业存入List中，每一项包含标号和时间
			JobNode jb = new JobNode(i + 1, a[i]);
			d.add(jb);
		}
		Collections.sort(d);// 对作业的List进行排序
		LinkedList<MachineNode> h = new LinkedList<>();// h保存所有的机器
		for (int i = 0; i < m; i++) {// 将所有的机器存入LinkedList中
			MachineNode x = new MachineNode(i + 1, 0);// 初始时，每台机器的空闲时间（完成上一个作业的时间）都为0
			h.add(x);
		}

		for (int i = 0; i < n; i++) {
			Collections.sort(h);
			MachineNode x = h.peek();
			System.out.println("将机器" + x.id + "从" + x.avail + "到" + (x.avail + d.get(i).time) + "的时间段分配给作业" + d.get(i).id);
			x.avail += d.get(i).time;
			sum = x.avail;
		}
		return sum;
	}
	
/**
 * 	设有n个独立的作业{1, 2, …, n}, 
 *  由m台相同的机器进行加工处理. 
 *  作业i所需时间为ti。
 *  约定:任何作业可以在任何一台机器上加工处理, 
 *  但未完工前不允许中断处理,
 *  任何作业不能拆分成更小的子作业。
 *  要求给出一种作业调度方案，
 *  使所给的 n 
 *  个作业在尽可能短的时间内由 m 台机器加工处理完成。
 *  多机调度问题是一个 NP 完全问题，到目前为止还没有完全有效的解法。对于这类问题，用贪心选择策略有时可以设计出一个比较好的近似算法。
 * 
 * 
 *   多任务调度  思路
 *   定义	每个数组里面都是一个任务跑起来需要的事件。
 *   定义    机器数量
 * 	
 * 	如果机器大于等于任务数量 那不需要进行最优调整，直接每个任务给一台机器 就可以了。
 * 
 * 	如果机器小于任务数量则用下面方式实现
 *  每个机器在执行该任务需要的事件之后，进行一次排序，将最小耗时的那个机器再来做新任务
 *  
 *  this.greedy(new int[] {12,33,12,3,1}, 3);
 *  将机器1从0到33的时间段分配给作业2
*	将机器2从0到12的时间段分配给作业1
*	将机器3从0到12的时间段分配给作业3
*	将机器3从12到15的时间段分配给作业4
*	将机器2从12到13的时间段分配给作业5
 *  
 *  有n个任务每个任务消耗机器时间不定，有n台机器。
 *  	要求给出一种作业调度方案，使最短时间做出完成任务
 * 1.定义每个任务消耗的时间数组
 * 2.定义机器数量
 * 
 * 	如果机器大于等于任务数量 那不需要进行最优调整，直接每个任务给一台机器 就可以了。
 * 
 * 	如果机器数量小于任务数量则用下面方式实现
 * 
 *  每个机器在执行该任务需要的事件之后，进行一次排序，将最小耗时的那个机器再来做新任务
 *  
 * *  this.greedy(new int[] {12,33,12,3,1}, 3);
 *  将机器1从0到33的时间段分配给作业2
 *	将机器2从0到12的时间段分配给作业1
 *	将机器3从0到12的时间段分配给作业3
 *	将机器3从12到15的时间段分配给作业4
 *	将机器2从12到13的时间段分配给作业5
 * <code>
 * 	将机器0从0到33分配给1
 * 	将机器1从0到12分配给0
 * 	将机器2从0到12分配给2
 * 	将机器2从12到3分配给3
 *  将机器1从12到1分配给4
 * </code>
 * 	
 * 	
 * @param machineNum
 * @param jobTime
 * @return
 */
	public int greedy2(int machineNum,int...jobTime) {
		
		int jobNum = jobTime.length;
		int totalTime = 0;
		if(machineNum>=jobNum) {
			for (int j = 0; j < jobNum; j++) {
				totalTime += jobTime[j];				
			} 
			System.out.println("每台机器一个任务~");
			return totalTime;
		}
		/**
		 * 将所有任务封装在 集合里面
		 */
		List<JobNode> jobArr = new ArrayList<>();
		for (int i = 0; i < jobNum; i++) {
			JobNode job = new JobNode(i, jobTime[i]);
			jobArr.add(job);
		}
		
		List<MachineNode> machineArr = new ArrayList<>();
		for (int i = 0; i < machineNum; i++) {
			/**
			 * 每台机器初始化1
			 */
			machineArr.add(new MachineNode(i, 0));
			
		}
		/**
		 * 每台机器搞最耗时的任务 降序
		 */
		Collections.sort(jobArr);
		/**
		 * 下面用机器来消耗任务
		 */
		for (int i = 0; i <jobNum ; i++) {
			/**
			 * 永远找第一台机器来消耗 一个任务。
			 */
			MachineNode machineNode = machineArr.get(0);
			JobNode jobNode = jobArr.get(i);
			/**
			 * 把时间加到机器耗时里面
			 */
			String val = "将机器"+machineNode.id+"从"+machineNode.avail+"到"+jobNode.time+"分配给"+jobNode.id;
			machineNode.avail += jobNode.time;
			//获取总消耗时间啊
			totalTime=machineNode.avail;
			/**
			 * 升序
			 */
			Collections.sort(machineArr);
			System.out.println(val);
		}
	
		return totalTime;
	}
//----------------------------多机调度第二遍------------------//
	
	public void greedy(int machinenumber , int ... jobtime) {
		/**
		 * 总耗时
		 */
		int totalTime = 0;
		if(machinenumber >= jobtime.length) {
			for (int i = 0; i < jobtime.length; i++) {
			  totalTime +=	jobtime[i];
			}
			System.out.println("每个机器分配一个任务 ，总耗时为"+totalTime);
			return;
		}
		
		/**
		 * 按降序排列，第一台机器执行最耗时的任务 以此类推。
		 */
		Arrays.sort(jobtime);
		
		
		
	}
	
	
	
//------------three-----------------------------------------//
	
	public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });

        int count = 0, curtime = 0;
        for(int i = 0;i < courses.length;i++){
            //若可选，增加当前时间，并且将当前课程放入courses中
            //否则，从courses中选一个耗时最长的课程，若这个耗时最长的课程比当前课程还长，则替换
            if(curtime + courses[i][0] <= courses[i][1]){
                courses[count++] = courses[i];
                curtime += courses[i][0];
            }else{
                int max_i = i;
                for(int j = count - 1;j >= 0;j--){
                    if(courses[j][0] > courses[max_i][0])   max_i = j;
                }
                if(courses[max_i][0] > courses[i][0]){
                    curtime += courses[i][0] - courses[max_i][0];
                    courses[max_i] = courses[i];
                }
            }
        }

        return count;
    }

}

class JobNode implements Comparable<JobNode> {
	int id;// 作业的标号
	int time;// 作业时间

	public JobNode(int id, int time) {
		this.id = id;
		this.time = time;
	}

	@Override
	public int compareTo(JobNode x) {// 按时间从大到小排列
		int times = x.time;
		return Integer.compare(times, time);
	}
}

class MachineNode implements Comparable<MachineNode> {
	int id;// 机器的标号
	int avail;// 机器空闲的时间（即机器做完某一项工作的时间）

	public MachineNode(int id, int avail) {
		this.id = id;
		this.avail = avail;
	}

	@Override
	public int compareTo(MachineNode o) {// 升序排序，LinkedList的first为最小的
		int xs = o.avail;
		return Integer.compare(avail, xs);
	}
}