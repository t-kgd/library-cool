package jp.gr.java_conf.kgd.library.cool.util.math;

public class Geometry2DHelper
{
	private static final double EPS = 1e-6;
	
	public static class ResultBufferIntersectsSegmentSegment
	{
		
	}
	
	//doubleの比較
	private static boolean equals(double a, double b)
	{
		return b - EPS < a && a < b + EPS;
	}
	
	private static boolean lessThan(double a, double b)
	{
		return a < b;
	}
	
	private static boolean lessThanEqual(double a, double b)
	{
		return a < b + EPS;
	}
	
	private static boolean greaterThan(double a, double b)
	{
		return b < a;
	}
	
	private static boolean greaterThanEqual(double a, double b)
	{
		return b - EPS < a;
	}
	
	//組の比較
	private static boolean equalsTuple(double a_x, double a_y, double b_x, double b_y)
	{
		return equals(a_x, b_x) && equals(a_y, b_y);
	}
	
	
	
	//ベクトル
	public static double angle(double vector_x, double vector_y)
	{
		return Math.atan2(vector_y, vector_x);
	}
	
	public static double lengthSquare(double vector_x, double vector_y)
	{
		return vector_x * vector_x + vector_y * vector_y;
	}
	
	public static double length(double vector_x, double vector_y)
	{
		return Math.sqrt(lengthSquare(vector_x, vector_y));
	}
	
	
	
	//ベクトル：ベクトル
	public static double dot(
			double vector1_x, double vector1_y,
			double vector2_x, double vector2_y)
	{
		return vector1_x * vector2_x + vector1_y * vector2_y;
	}
	
	public static double cross(
			double vector1_x, double vector1_y,
			double vector2_x, double vector2_y)
	{
		return vector1_x * vector2_y - vector1_y * vector2_x;
	}
	
	
	
	//点：点
	public static double distanceSquarePointPoint(
			double point1_x, double point1_y,
			double point2_x, double point2_y)
	{
		return length(point1_x - point2_x, point1_y - point2_y);
	}
	
	public static double distancePointPoint(
			double point1_x, double point1_y,
			double point2_x, double point2_y)
	{
		return Math.sqrt(distanceSquarePointPoint(point1_x, point1_y, point2_x, point2_y));
	}
	
	public static boolean disjointsPointPoint(
			double point1_x, double point1_y,
			double point2_x, double point2_y)
	{
		return !intersectsPointPoint(point1_x, point1_y, point2_x, point2_y);
	}
	
	public static boolean intersectsPointPoint(
			double point1_x, double point1_y,
			double point2_x, double point2_y)
	{
		return equalsTuple(point1_x, point1_y, point2_x, point2_y);
	}
	
	public static boolean containsPointPoint(
			double point1_x, double point1_y,
			double point2_x, double point2_y)
	{
		return intersectsPointPoint(point1_x, point1_y, point2_x, point2_y);
	}

	//点：円
	public static double distancePointCircle(
			double point_x, double point_y,
			double circle_x, double circle_y, double circle_rad)
	{
		return distancePointPoint(point_x, point_y, circle_x, circle_y) - circle_rad;
	}

	public static boolean disjointsPointCircle(
			double point_x, double point_y, 
			double circle_x, double circle_y, double circle_rad)
	{
		return greaterThan(distancePointCircle(point_x, point_y, circle_x, circle_y, circle_rad), 0);
	}

	public static boolean intersectsPointCircle(
			double point_x, double point_y,
			double circle_x, double circle_y, double circle_rad)
	{
		return equals(distancePointCircle(point_x, point_y, circle_x, circle_y, circle_rad), 0);
	}

	public static boolean containsPointCircle(
			double point_x, double point_y,
			double circle_x, double circle_y, double circle_rad)
	{
		if(greaterThan(circle_rad, 0)) return false;

		return containsPointPoint(point_x, point_y, circle_x, circle_y);
	}
	
	//円：点 (contains以外は 点：円 に移譲)
	public static double distanceCirclePoint(
			double circle_x, double circle_y, double circle_rad,
			double point_x, double point_y)
	{
		return distancePointCircle(point_x, point_y, circle_x, circle_y, circle_rad);
	}

	public static boolean disjointsCirclePoint(
			double circle_x, double circle_y, double circle_rad,
			double point_x, double point_y)
	{
		return disjointsPointCircle(point_x, point_y, circle_x, circle_y, circle_rad);
	}

	public static boolean intersectsCirclePoint(
			double circle_x, double circle_y, double circle_rad, 
			double point_x, double point_y)
	{
		return intersectsPointCircle(point_x, point_y, circle_x, circle_y, circle_rad);
	}

	public static boolean containsCirclePoint(
			double circle_x, double circle_y, double circle_rad,
			double point_x, double point_y)
	{
		if(lessThanEqual(circle_rad, 0))
		{
			if(equals(circle_rad, 0))
			{
				if(equalsTuple(point_x, point_y, circle_x, circle_y)) return true;
			}

			return false;
		}
		
		return lessThanEqual(distanceSquarePointPoint(point_x, point_y, circle_x, circle_y), circle_rad * circle_rad);
	}

	//円：円
	public static double distanceCircleCircle(
			double circle1_x, double circle1_y, double circle1_rad,
			double circle2_x, double circle2_y, double circle2_rad)
	{
		return distanceCirclePoint(circle1_x, circle1_y, circle1_rad + circle2_rad, circle2_x, circle2_y);
	}
	
	public static boolean disjointsCircleCircle(
			double circle1_x, double circle1_y, double circle1_rad,
			double circle2_x, double circle2_y, double circle2_rad)
	{	
		return disjointsCirclePoint(circle1_x, circle1_y, circle1_rad + circle2_rad, circle2_x, circle2_y);
	}
	
	public static boolean intersectsCircleCircle(
			double circle1_x, double circle1_y, double circle1_rad,
			double circle2_x, double circle2_y, double circle2_rad)
	{
		double r = circle1_rad + circle2_rad;		
		if(lessThanEqual(r, 0))
		{
			if(equals(r, 0))
			{
				if(equalsTuple(circle1_x, circle1_y, circle2_x, circle2_y)) return true;
			}
			
			return false;
		}
		
		double dd = distanceSquarePointPoint(circle1_x, circle1_y, circle2_x, circle2_y);
		
		if(greaterThan(dd, r * r)) return false;
		
		double d = Math.sqrt(dd);
		
		return greaterThanEqual(d + circle1_rad, circle2_rad) &&
				greaterThanEqual(d + circle2_rad, circle1_rad);
	}
	
	public static boolean containsCircleCircle(
			double circle1_x, double circle1_y, double circle1_rad,
			double circle2_x, double circle2_y, double circle2_rad)
	{	
		return containsCirclePoint(circle1_x, circle1_y, circle1_rad - circle2_rad, circle2_x, circle2_y);
	}

	//点：矩形
	public static double distancePointRect(
			double point_x, double point_y,
			double rect_x, double rect_y, double rect_width, double rect_height)
	{	
		boolean left_flag = lessThan(point_x, rect_x);
		boolean top_flag = lessThan(point_y, rect_y);

		if(left_flag && top_flag)
		{
			return distancePointPoint(point_x, point_y, rect_x, rect_y);
		}

		double right = rect_x + rect_width;
		boolean right_flag = lessThan(right, point_x);

		if(right_flag && top_flag)
		{
			return distancePointPoint(point_x, point_y, right, rect_y);
		}

		double bottom = rect_y + rect_height;
		boolean bottom_flag = lessThan(bottom, point_y);

		if(left_flag && bottom_flag)
		{
			return distancePointPoint(point_x, point_y, rect_x, bottom);
		}

		if(right_flag && bottom_flag)
		{
			return distancePointPoint(point_x, point_y, right, bottom);
		}

		if(!left_flag && !right_flag && !top_flag && bottom_flag)
		{
			return Math.max(
					Math.abs(point_y - (rect_x + rect_width / 2)) - rect_height,
					Math.abs(point_x - (rect_y + rect_height / 2)) - rect_width);
		}

		if(!left_flag && !right_flag)
		{
			return Math.abs(point_y - (rect_x + rect_width / 2)) - rect_height;
		}

		//if(!top_flag && !bottom_flag)
			return Math.abs(point_x - (rect_y + rect_height / 2)) - rect_width;
	}

	public static boolean disjointsPointRect(
			double point_x, double point_y,
			double rect_x, double rect_y, double rect_width, double rect_height)
	{
		return lessThan(point_x, rect_x) || lessThan(rect_x + rect_width, point_x) ||
				lessThan(point_y, rect_y) || lessThan(rect_y + rect_height, point_y);
	}

	public static boolean intersectsPointRect(
			double point_x, double point_y,
			double rect_x, double rect_y, double rect_width, double rect_height)
	{
		double right = rect_x + rect_width;
		double bottom = rect_y + rect_height;

		if(lessThan(point_x, rect_x) || lessThan(right, point_x) ||
				lessThan(point_y, rect_y) || lessThan(bottom, point_y)) return false;		

		return equals(point_x, rect_x) || equals(point_x, right) ||
				equals(point_y, rect_y) || equals(point_y, bottom);
	}

	public static boolean containsPointRect(
			double point_x, double point_y,
			double rect_x, double rect_y, double rect_width, double rect_height)
	{
		if(lessThan(rect_width, 0) || lessThan(rect_height, 0)) return false;
		
		return containsPointPoint(point_x, point_y, rect_x, rect_y);
	}

	//矩形：点（contains以外は 点：矩形 に委譲）
	public static double distanceRectPoint(
			double rect_x, double rect_y, double rect_width, double rect_height,
			double point_x, double point_y)
	{
		return distancePointRect(point_x, point_y, rect_x, rect_y, rect_width, rect_height);
	}

	public static boolean disjointsRectPoint(
			double rect_x, double rect_y, double rect_width, double rect_height,
			double point_x, double point_y)
	{
		return disjointsPointRect(point_x, point_y, rect_x, rect_y, rect_width, rect_height);
	}

	public static boolean intersectsRectPoint(
			double rect_x, double rect_y, double rect_width, double rect_height,
			double point_x, double point_y)
	{
		return intersectsPointRect(point_x, point_y, rect_x, rect_y, rect_width, rect_height);
	}

	public static boolean containsRectPoint(
			double rect_x, double rect_y, double rect_width, double rect_height,
			double point_x, double point_y)
	{
		return greaterThanEqual(point_x, rect_x) && greaterThanEqual(rect_x + rect_width, point_x) &&
				greaterThanEqual(point_y, rect_y) && greaterThanEqual(rect_y + rect_height, point_y);
	}

	//矩形：矩形
	public static boolean disjointsRectRect(
			double rect1_x, double rect1_y, double rect1_width, double rect1_height,
			double rect2_x, double rect2_y, double rect2_width, double rect2_height)
	{	
		return greaterThan(Math.abs(rect1_x - rect2_x), (rect1_width + rect2_width) / 2) ||
				greaterThan(Math.abs(rect1_y - rect2_y), (rect1_height + rect2_height) / 2);
	}
	
	public static boolean intersectsRectRect(
			double rect1_x, double rect1_y, double rect1_width, double rect1_height,
			double rect2_x, double rect2_y, double rect2_width, double rect2_height)
	{	
		double dx = Math.abs(rect1_x - rect2_x);
		double dy = Math.abs(rect1_y - rect2_y);
		
		double w1 = rect1_width / 2;
		double h1 = rect1_height / 2;
		
		double w2 = rect2_width / 2;
		double h2 = rect2_height / 2;
		
		if(greaterThan(dx, w1 + w2) || greaterThan(dy, h1 + h2)) return false;
		
		return (greaterThanEqual(dx + w2, w1) || greaterThanEqual(dy + h2, h1)) &&
				(greaterThanEqual(dx + w1, w2) || greaterThanEqual(dy + h1, h2));
	}
	
	public static boolean containsRectRect(
			double rect1_x, double rect1_y, double rect1_width, double rect1_height,
			double rect2_x, double rect2_y, double rect2_width, double rect2_height)
	{
		double w = rect2_width / 2;
		double h = rect2_height / 2;
		
		return containsRectPoint(rect1_x, rect1_y, rect1_width - w, rect1_height - h, rect2_x + w, rect2_y + h);
	}


	//点：直線
	public static double distancePointLine(
			double point_x, double point_y,
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y)
	{
		return cross(line_vector_x, line_vector_y, point_x - line_start_x, point_y - line_start_y) /
				length(line_vector_x, line_vector_y);
	}
	
	public static boolean disjointsPointLine(
			double point_x, double point_y,
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y)
	{
		return !intersectsPointLine(
				point_x, point_y,
				line_start_x, line_start_y, line_vector_x, line_vector_y);
	}
	
	public static boolean intersectsPointLine(
			double point_x, double point_y,
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y)
	{
		return cross(line_vector_x, line_vector_y,
						point_x - line_start_x, point_y - line_start_y) == 0;
	}
	
	public static boolean containsPointLine(
			double point_x, double point_y,
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y)
	{
		if(!equalsTuple(line_vector_x, line_vector_y, 0, 0)) return false;
		
		return equalsTuple(point_x, point_y, line_start_x, line_start_y);
	}
	
	//直線：点（contains以外は 点：直線 に委譲）
	public static double distanceLinePoint(
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y,
			double point_x, double point_y)
	{
		return distancePointLine(
				point_x, point_y,
				line_start_x, line_start_y, line_vector_x, line_vector_y);
	}
	
	public static boolean disjointsLinePoint(
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y,
			double point_x, double point_y)
	{
		return disjointsPointLine(
				line_start_x, line_start_y,
				line_vector_x, line_vector_y, point_x, point_y);
	}
	
	public static boolean intersectsLinePoint(
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y,
			double point_x, double point_y)
	{
		return intersectsPointLine(
				line_start_x, line_start_y,
				line_vector_x, line_vector_y, point_x, point_y);
	}
	
	public static boolean containsLinePoint(
			double line_start_x, double line_start_y, double line_vector_x, double line_vector_y,
			double point_x, double point_y)
	{
		return intersectsLinePoint(
				line_start_x, line_start_y,
				line_vector_x, line_vector_y, point_x, point_y);
	}
	
	//点：線分
	public static double distancePointSegment(
			double point_x, double point_y,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		double vector1_x = seg_end_x - seg_start_x;
		double vector1_y = seg_end_y - seg_start_y;

		double vector2_x = point_x - seg_start_x;
		double vector2_y = point_y - seg_start_y;

		if(dot(vector1_x, vector1_y, vector2_x, vector2_y) < 0)
		{
			return length(vector1_x, vector1_y);
		}

		vector1_x *= -1;
		vector1_y *= -1;

		vector2_x = point_x - seg_end_x;
		vector2_y = point_y - seg_end_y;

		if(dot(vector1_x, vector1_y, vector2_x, vector2_y) < 0)
		{
			return length(vector2_x, vector2_y);
		}

		return Math.abs(
				cross(vector1_x, vector1_y, vector2_x, vector2_y) /
				length(vector1_x, vector1_y));
	}
	
	public static boolean disjointsPointSegment(
			double point_x, double point_y,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		return intersectsPointSegment(
				point_x, point_y,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean intersectsPointSegment(
			double point_x, double point_y,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		double vector1_x = seg_end_x - seg_start_x;
		double vector1_y = seg_end_y - seg_start_y;

		double vector2_x = point_x - seg_start_x;
		double vector2_y = point_y - seg_start_y;
		
		double d = dot(vector1_x, vector1_y, vector2_x, vector2_y);
		double l1 = length(vector1_x,vector1_y);
		double l2 = length(vector2_x,vector2_y);
		
		return equals(d, l1 * l2) && lessThanEqual(l2, l1);
	}
	
	public static boolean containsPointSegment(
			double point_x, double point_y,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		if(!equalsTuple(seg_start_x, seg_start_y, seg_end_x, seg_end_y)) return false;
		
		return equalsTuple(point_x, point_y, seg_start_x, seg_start_y);
	}
	
	//線分：点（contains以外は 点：線分 に委譲）
	public static double distanceSegmentPoint(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double point_x, double point_y)
	{
		return distancePointSegment(
				point_x, point_y,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean disjointsSegmentPoint(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double point_x, double point_y)
	{
		return disjointsPointSegment(
				point_x, point_y,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean intersectsSegmentPoint(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double point_x, double point_y)
	{
		return intersectsPointSegment(
				point_x, point_y,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean containsSegmentPoint(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double point_x, double point_y)
	{
		return intersectsSegmentPoint(
				point_x, point_y,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}

	//線分：線分
	public static double distanceSegmentSegment(
			double seg1_start_x, double seg1_start_y, double seg1_end_x, double seg1_end_y,
			double seg2_start_x, double seg2_start_y, double seg2_end_x, double seg2_end_y)
	{
		double vector1_x = seg1_end_x - seg1_start_x;
		double vector1_y = seg1_end_y - seg1_start_y;
		
		double vector2_x = seg2_end_x - seg2_start_x;
		double vector2_y = seg2_end_y - seg2_start_y;
		
		double v1_v2_cross = cross(vector1_x, vector1_y, vector2_x, vector2_y);
		if(v1_v2_cross == 0)
		{
			if(equalsTuple(vector1_x, vector1_y, vector2_x, vector2_y))
			{
				return 0;
			}
			
			return distancePointPoint(seg1_start_x, seg1_start_y, seg2_start_x, seg2_start_y);
		}
		
		double vector_x = seg2_start_x - seg1_start_x;
		double vector_y = seg2_start_y - seg1_start_y;	
		
		double v_v1_cross = cross(vector_x, vector_y, vector1_x, vector1_y);
		double v_v2_cross = cross(vector_x, vector_y, vector2_x, vector2_y);

		double t1 = v_v2_cross / v1_v2_cross;
		double t2 = v_v1_cross / v1_v2_cross;
		
		if(lessThanEqual(0, t1) || lessThanEqual(t1, 1) ||
				lessThanEqual(0, t2) || lessThanEqual(t2, 1))
		{
			return 0;
		}
		
		double min = distancePointSegment(
				seg1_start_x, seg1_start_y,
				seg2_start_x, seg2_start_y, seg2_end_x, seg2_end_y);
		
		double buf = distancePointSegment(
				seg1_end_x, seg1_end_y,
				seg2_start_x, seg2_start_y, seg2_end_x, seg2_end_y);
		
		if(lessThan(buf, min)) min = buf;
		
		buf = distancePointSegment(
				seg2_start_x, seg2_start_y,
				seg1_start_x, seg1_start_y, seg1_end_x, seg1_end_y);
		
		if(lessThan(buf, min)) min = buf;
		
		buf = distancePointSegment(
				seg2_end_x, seg2_end_y,
				seg1_start_x, seg1_start_y, seg1_end_x, seg1_end_y);
		
		if(lessThan(buf, min)) min = buf;
		
		return min;
	}
	
	public static boolean disjointsSegmentSegment(
			double seg1_start_x, double seg1_start_y, double seg1_end_x, double seg1_end_y,
			double seg2_start_x, double seg2_start_y, double seg2_end_x, double seg2_end_y)
	{
		return !intersectsSegmentSegment(
				seg1_start_x, seg1_start_y, seg1_end_x, seg1_end_y,
				seg2_start_x, seg2_start_y, seg2_end_x, seg2_end_y);
	}
	
	public static boolean intersectsSegmentSegment(
			double seg1_start_x, double seg1_start_y, double seg1_end_x, double seg1_end_y,
			double seg2_start_x, double seg2_start_y, double seg2_end_x, double seg2_end_y)
	{	
		double vector1_x = seg1_end_x - seg1_start_x;
		double vector1_y = seg1_end_y - seg1_start_y;
		
		double vector2_x = seg2_end_x - seg2_start_x;
		double vector2_y = seg2_end_y - seg2_start_y;
		
		double v1_v2_cross = cross(vector1_x, vector1_y, vector2_x, vector2_y);
		if(v1_v2_cross == 0)
		{
			if(equalsTuple(vector1_x, vector1_y, vector2_x, vector2_y))
			{
				return true;
			}
			
			return false;
		}
		
		double vector_x = seg2_start_x - seg1_start_x;
		double vector_y = seg2_start_y - seg1_start_y;	
		
		double v_v1_cross = cross(vector_x, vector_y, vector1_x, vector1_y);
		double v_v2_cross = cross(vector_x, vector_y, vector2_x, vector2_y);

		double t1 = v_v2_cross / v1_v2_cross;
		if(lessThan(t1, 0) || lessThan(1, t1)) return false;
				
		double t2 = v_v1_cross / v1_v2_cross;
		if(lessThan(t2, 0) || lessThan(1, t2)) return false;
		
		return true;
	}
	
	public static boolean containsSegmentSegment(
			double seg1_start_x, double seg1_start_y, double seg1_end_x, double seg1_end_y,
			double seg2_start_x, double seg2_start_y, double seg2_end_x, double seg2_end_y)
	{
		if(distanceSquarePointPoint(seg1_start_x, seg1_start_y, seg1_end_x, seg1_end_y) <
				distanceSquarePointPoint(seg2_start_x, seg2_start_y, seg2_end_x, seg2_end_y))
		{
			return false;
		}
		
		double vector1_x = seg1_end_x - seg1_start_x;
		double vector1_y = seg1_end_y - seg1_start_y;

		double vector2_x = seg2_start_x - seg1_start_x;
		double vector2_y = seg2_start_y - seg1_start_y;
		
		double d = dot(vector1_x, vector1_y, vector2_x, vector2_y);
		double l1 = length(vector1_x,vector1_y);
		double l2 = length(vector2_x,vector2_y);
		
		if(!equals(d, l1 * l2) || !lessThanEqual(l2, l1)) return false;
		
		vector2_x = seg2_end_x - seg1_start_x;
		vector2_y = seg2_end_y - seg1_start_y;
		
		d = dot(vector1_x, vector1_y, vector2_x, vector2_y);
		l2 = length(vector2_x,vector2_y);
		
		return equals(d, l1 * l2) && lessThanEqual(l2, l1);
	}

	
	
	
	
	//円：線分
	public static double distanceCircleSegment(
			double circle_x, double circle_y, double circle_rad,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		return distancePointSegment(
				circle_x, circle_y,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y) - circle_rad;
	}
	
	public static boolean disjointsCircleSegment(
			double circle_x, double circle_y, double circle_rad,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		return greaterThan(distanceCircleSegment(
				circle_x, circle_y, circle_rad,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y), 0);
	}
	
	public static boolean intersectsCircleSegment(
			double circle_x, double circle_y, double circle_rad,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		if(containsCircleSegment(
				circle_x, circle_y, circle_rad,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y))
		{
			return false;
		}
		
		return !disjointsCircleSegment(
				circle_x, circle_y, circle_rad,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean containsCircleSegment(
			double circle_x, double circle_y, double circle_rad,
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y)
	{
		double rr = circle_rad * circle_rad;
		return lessThanEqual(distanceSquarePointPoint(circle_x, circle_y, seg_start_x, seg_start_y), rr) ||
				lessThanEqual(distanceSquarePointPoint(circle_x, circle_y, seg_end_x, seg_end_y), rr);			
	}
	
	//線分：円（contains以外 円：線分 に委譲）
	public static double distanceSegmentCircle(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double circle_x, double circle_y, double circle_rad)
	{
		return distanceCircleSegment(
				circle_x, circle_y, circle_rad,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean disjointsSegmentCircle(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double circle_x, double circle_y, double circle_rad)
	{
		return disjointsCircleSegment(
				circle_x, circle_y, circle_rad,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean intersectsSegmentCircle(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double circle_x, double circle_y, double circle_rad)
	{
		return intersectsCircleSegment(
				circle_x, circle_y, circle_rad,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
	
	public static boolean containsSegmentCircle(
			double seg_start_x, double seg_start_y, double seg_end_x, double seg_end_y,
			double circle_x, double circle_y, double circle_rad)
	{
		return containsCircleSegment(
				circle_x, circle_y, circle_rad,
				seg_start_x, seg_start_y, seg_end_x, seg_end_y);
	}
}
