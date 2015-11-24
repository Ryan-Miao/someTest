package com.test.util;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Geohash {

	private static int numbits = 6 * 5;
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	final static HashMap<Character, Integer> lookup = new HashMap<Character, Integer>();
	static {
		int i = 0;
		for (char c : digits)
			lookup.put(c, i++);

	}

	public Geohash() {
		setMap();
	}

	public double[] decode(String geohash) {
		StringBuilder buffer = new StringBuilder();
		for (char c : geohash.toCharArray()) {

			int i = lookup.get(c) + 32;
			buffer.append(Integer.toString(i, 2).substring(1));
		}

		BitSet lonset = new BitSet();
		BitSet latset = new BitSet();

		// even bits
		int j = 0;
		for (int i = 0; i < numbits * 2; i += 2) {
			boolean isSet = false;
			if (i < buffer.length())
				isSet = buffer.charAt(i) == '1';
			lonset.set(j++, isSet);
		}

		// odd bits
		j = 0;
		for (int i = 1; i < numbits * 2; i += 2) {
			boolean isSet = false;
			if (i < buffer.length())
				isSet = buffer.charAt(i) == '1';
			latset.set(j++, isSet);
		}

		double lon = decode(lonset, -180, 180);
		double lat = decode(latset, -90, 90);

		return new double[] { lat, lon };
	}

	private double decode(BitSet bs, double floor, double ceiling) {
		double mid = 0;
		for (int i = 0; i < bs.length(); i++) {
			mid = (floor + ceiling) / 2;
			if (bs.get(i))
				floor = mid;
			else
				ceiling = mid;
		}
		return mid;
	}

	public String encode(String lat, String lon) {
		if (StringUtils.isEmpty(lat) || StringUtils.isEmpty(lon)) {
			return "";
		}
		return encode(Double.parseDouble(lat), Double.parseDouble(lon));

	}

	public String encode(double lat, double lon) {
		BitSet latbits = getBits(lat, -90, 90);
		BitSet lonbits = getBits(lon, -180, 180);
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < numbits; i++) {
			buffer.append((lonbits.get(i)) ? '1' : '0');
			buffer.append((latbits.get(i)) ? '1' : '0');
		}
		return base32(Long.parseLong(buffer.toString(), 2));
	}

	private BitSet getBits(double lat, double floor, double ceiling) {
		BitSet buffer = new BitSet(numbits);
		for (int i = 0; i < numbits; i++) {
			double mid = (floor + ceiling) / 2;
			if (lat >= mid) {
				buffer.set(i);
				floor = mid;
			} else {
				ceiling = mid;
			}
		}
		return buffer;
	}

	public static String base32(long i) {
		char[] buf = new char[65];
		int charPos = 64;
		boolean negative = (i < 0);
		if (!negative)
			i = -i;
		while (i <= -32) {
			buf[charPos--] = digits[(int) (-(i % 32))];
			i /= 32;
		}
		buf[charPos] = digits[(int) (-i)];

		if (negative)
			buf[--charPos] = '-';
		return new String(buf, charPos, (65 - charPos));
	}

	/*********************** 获取九个的矩形编码 ****************************************/
	public static String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";
	public static Map<String, String> BORDERS = new HashMap<String, String>();
	public static Map<String, String> NEIGHBORS = new HashMap<String, String>();

	public static void setMap() {
		NEIGHBORS.put("right:even", "bc01fg45238967deuvhjyznpkmstqrwx");
		NEIGHBORS.put("left:even", "238967debc01fg45kmstqrwxuvhjyznp");
		NEIGHBORS.put("top:even", "p0r21436x8zb9dcf5h7kjnmqesgutwvy");
		NEIGHBORS.put("bottom:even", "14365h7k9dcfesgujnmqp0r2twvyx8zb");

		NEIGHBORS.put("right:odd", "p0r21436x8zb9dcf5h7kjnmqesgutwvy");
		NEIGHBORS.put("left:odd", "14365h7k9dcfesgujnmqp0r2twvyx8zb");
		NEIGHBORS.put("top:odd", "bc01fg45238967deuvhjyznpkmstqrwx");
		NEIGHBORS.put("bottom:odd", "238967debc01fg45kmstqrwxuvhjyznp");

		BORDERS.put("right:even", "bcfguvyz");
		BORDERS.put("left:even", "0145hjnp");
		BORDERS.put("top:even", "prxz");
		BORDERS.put("bottom:even", "028b");

		BORDERS.put("right:odd", "prxz");
		BORDERS.put("left:odd", "028b");
		BORDERS.put("top:odd", "bcfguvyz");
		BORDERS.put("bottom:odd", "0145hjnp");

	}

	/**
	 * 获取九个点的矩形编码
	 * 
	 * @param geohash
	 * @return
	 */
	public String[] getGeoHashExpand(String geohash) {
		try {
			String geohashTop = calculateAdjacent(geohash, "top");
			String geohashBottom = calculateAdjacent(geohash, "bottom");
			String geohashRight = calculateAdjacent(geohash, "right");
			String geohashLeft = calculateAdjacent(geohash, "left");
			String geohashTopLeft = calculateAdjacent(geohashLeft, "top");
			String geohashTopRight = calculateAdjacent(geohashRight, "top");
			String geohashBottomRight = calculateAdjacent(geohashRight,
					"bottom");
			String geohashBottomLeft = calculateAdjacent(geohashLeft, "bottom");
			String[] expand = { geohash, geohashTop, geohashBottom,
					geohashRight, geohashLeft, geohashTopLeft, geohashTopRight,
					geohashBottomRight, geohashBottomLeft };
			return expand;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 分别计算每个点的矩形编码
	 * 
	 * @param srcHash
	 * @param dir
	 * @return
	 */
	public static String calculateAdjacent(String srcHash, String dir) {
		srcHash = srcHash.toLowerCase();
		char lastChr = srcHash.charAt(srcHash.length() - 1);
		int a = srcHash.length() % 2;
		String type = (a > 0) ? "odd" : "even";
		String base = srcHash.substring(0, srcHash.length() - 1);
		if (BORDERS.get(dir + ":" + type).indexOf(lastChr) != -1) {
			base = calculateAdjacent(base, dir);
		}
		base = base
				+ BASE32.toCharArray()[(NEIGHBORS.get(dir + ":" + type)
						.indexOf(lastChr))];
		return base;
	}

	// @Deprecated
	// public static void expandLngLat(String geohash, int len){
	// boolean is_even = true;
	// double[] lat = new double[3];
	// double[] lon = new double[3];
	// lat[0] = -90.0;
	// lat[1] = 90.0;
	// lon[0] = -180.0;
	// lon[1] = 180.0;
	// double lat_err = 90.0;
	// double lon_err = 180.0;
	// char[] geohashChar = geohash.toCharArray();
	// // String[] BITS = {"16", "8", "4", "2", "1"};
	// int[] BITS = {16, 8, 4, 2, 1};
	// for (int i = 0; i < geohashChar.length; i++) {
	// char c = geohashChar[i];
	// int cd = BASE32.indexOf(c);
	// for (int j = 0; j < 5; j++) {
	// int mask = BITS[j];
	// if (is_even) {
	// lon_err /= 2;
	// refine_interval(lon, cd, mask);
	// } else {
	// lat_err /= 2;
	// refine_interval(lat, cd, mask);
	// }
	// is_even = !is_even;
	// }
	// }
	// lat[2] = (lat[0] + lat[1])/2;
	// //1:[38.8970947265625, 38.902587890625, 38.89984130859375]
	// //1: 38.8970947265625, 38.902587890625, 38.89984130859375
	// //2:[38.902587890625, 38.9080810546875, 38.90533447265625]
	// //2: 38.902587890625, 38.9080810546875, 38.90533447265625
	// lon[2] = (lon[0] + lon[1])/2;
	// //1:[-77.047119140625, -77.0361328125, -77.0416259765625]
	// //1: -77.047119140625, -77.0361328125, -77.0416259765625
	// //2:[-77.047119140625, -77.0361328125, -77.0416259765625]
	// //2: -77.047119140625, -77.0361328125, -77.0416259765625
	//
	// String topLeft = lat[0]+","+lon[0];
	// String topRight = lat[0]+","+lon[1];
	//
	// String bottomleft = lat[1]+","+lon[0];
	// String bottoomRight = lat[1]+","+lon[1];
	// String centerPoint = (lat[0]+lat[1])/2+","+(lon[0]+lon[1])/2;
	//
	// String centerTop = lat[0]+","+(lon[0]+lon[1])/2;
	// String centerBottom = lat[1]+","+(lon[0]+lon[1])/2;
	//
	// String centerLeft = (lat[0]+lat[1])/2+","+lon[0];
	// String centerRight = (lat[0]+lat[1])/2+","+lon[1];
	// // System.out.println("topLeft:["+topLeft+"] geoHash:"+g.encode(lat[0],
	// lon[0]));
	// // System.out.println("topRight:["+topRight+"] geoHash:"+g.encode(lat[0],
	// lon[1]));
	// //
	// System.out.println("bottomleft:["+bottomleft+"] geoHash:"+g.encode(lat[1],
	// lon[0]));
	// //
	// System.out.println("bottoomRight:["+bottoomRight+"] geoHash:"+g.encode(lat[1],
	// lon[1]));
	// //
	// System.out.println("centerPoint:["+centerPoint+"] geoHash:"+g.encode((lat[0]+lat[1])/2,
	// (lon[0]+lon[1])/2));
	// //
	// System.out.println("centerTop:["+centerTop+"] geoHash:"+g.encode(lat[0],
	// (lon[0]+lon[1])/2));
	// //
	// System.out.println("centerBottom:["+centerBottom+"] geoHash:"+g.encode(lat[1],
	// (lon[0]+lon[1])/2));
	// //
	// System.out.println("centerLeft:["+centerLeft+"] geoHash:"+g.encode((lat[0]+lat[1])/2,
	// lon[0]));
	// //
	// System.out.println("centerRight:["+centerRight+"] geoHash:"+g.encode((lat[0]+lat[1])/2,
	// lon[1]));
	//
	// }
	//
	// @Deprecated
	// public static void refine_interval(double[] interval, int cd, int mask){
	// if ((cd & mask)>0){
	// interval[0] = (interval[0] + interval[1])/2;
	// }else{
	// interval[1] = (interval[0] + interval[1])/2;
	// }
	// }
	//

	// ****************************************************************************************************************

	private static final double EARTH_RADIUS = 6371;// 赤道半径(单位m)

	/**
	 * 转化为弧度(rad)
	 * */
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
	 * 
	 * @param lon1
	 *            第一点的精度
	 * @param lat1
	 *            第一点的纬度
	 * @param lon2
	 *            第二点的精度
	 * @param lat2
	 *            第二点的纬度
	 * @return 返回的距离，单位m
	 * */
	public static double getDistance(double lon1, double lat1, double lon2,
			double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000)/1000.0;
		return s;
	}

	/*
	 * 永相逢超市 108.83457500177 34.256981052624 wqj6us6cmkj5bbfj6qdg s6q08ubhhuq7
	 */
	public static void main(String[] args) throws Exception {

		// 东四站 灯市口站
		double lon1 = 116.4174628300;
		double lat1 = 39.9243669400;
		double lon2 = 116.4177739600;
		double lat2 = 39.9171260300;
		double dist;
		String geocode;

		dist = getDistance(lon1, lat1, lon2, lat2);
		System.out.println("两点相距：" + dist + " km");

		Geohash geohash = new Geohash();
		geocode = geohash.encode(lat1, lon1);
		System.out.println("当前位置编码：" + geocode);
		double[] decode = new Geohash().decode(geocode);
		for (double d : decode) {
			System.out.println(d);
		}

		geocode = geohash.encode(lat2, lon2);
		System.out.println("远方位置编码：" + geocode);
		decode = new Geohash().decode(geocode);
		for (double d : decode) {
			System.out.println(d);
		}

		/* 获取的geohash多少位，位数越长，精度越准 */
		int geohashLen = 5;

		/* 获取中心点的geohash */
		String code = geohash.encode(lat1, lon1).substring(0, geohashLen);

		/* 获取所有的矩形geohash， 一共是九个 ，包含中心点,打印顺序请参考图2 */
		String[] result = geohash.getGeoHashExpand(code);
		for (String string : result) {
			System.out.println(string);
		}

	}

}