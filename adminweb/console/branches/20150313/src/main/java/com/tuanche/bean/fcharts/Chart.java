package com.tuanche.bean.fcharts;

import java.io.Serializable;

public class Chart implements Serializable {
	
	/**
	 * 标题
	 */
	private String caption;
	/**
	 * y轴单位前缀
	 */
	private String numberprefix;
	
	/**
	 * y轴单位后缀
	 */
	private String numberSuffix;
	private String plotgradientcolor;
	/**
	 * 是否以K代替千,或以M代替百万
	 */
	private String formatNumberScale = "0";
	/**
	 * 背景色
	 */
	private String bgcolor = "FFFFFF";
	private String showalternatehgridcolor = "0";
	private String divlinecolor = "CCCCCC";
	/**
	 * 是否显示数值
	 */
	private String showvalues = "0";
	private String showcanvasborder = "0";
	private String canvasborderalpha = "0";
	private String canvasbordercolor = "CCCCCC";
	private String canvasborderthickness = "1";
	/**
	 * y轴最大值
	 */
//	private String yaxismaxvalue = "30000";
	/**
	 * 标题内边距
	 */
	private String captionpadding = "30";
	/**
	 * 折线的宽度
	 */
	private String linethickness = "2";
	private String yaxisvaluespadding = "15";
	private String legendshadow = "0";
	private String legendborderalpha = "0";
	/**
	 * 自定义图表折线的颜色
	 */
	private String palettecolors = "#f8bd19,#008ee4,#33bdda,#e44a00,#6baa01,#583e78";
	private String showborder = "0";
	
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getNumberprefix() {
		return numberprefix;
	}
	public void setNumberprefix(String numberprefix) {
		this.numberprefix = numberprefix;
	}
	public String getNumberSuffix() {
		return numberSuffix;
	}
	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}
	public String getPlotgradientcolor() {
		return plotgradientcolor;
	}
	public void setPlotgradientcolor(String plotgradientcolor) {
		this.plotgradientcolor = plotgradientcolor;
	}
	public String getFormatNumberScale() {
		return formatNumberScale;
	}
	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getShowalternatehgridcolor() {
		return showalternatehgridcolor;
	}
	public void setShowalternatehgridcolor(String showalternatehgridcolor) {
		this.showalternatehgridcolor = showalternatehgridcolor;
	}
	public String getDivlinecolor() {
		return divlinecolor;
	}
	public void setDivlinecolor(String divlinecolor) {
		this.divlinecolor = divlinecolor;
	}
	public String getShowvalues() {
		return showvalues;
	}
	public void setShowvalues(String showvalues) {
		this.showvalues = showvalues;
	}
	public String getShowcanvasborder() {
		return showcanvasborder;
	}
	public void setShowcanvasborder(String showcanvasborder) {
		this.showcanvasborder = showcanvasborder;
	}
	public String getCanvasborderalpha() {
		return canvasborderalpha;
	}
	public void setCanvasborderalpha(String canvasborderalpha) {
		this.canvasborderalpha = canvasborderalpha;
	}
	public String getCanvasbordercolor() {
		return canvasbordercolor;
	}
	public void setCanvasbordercolor(String canvasbordercolor) {
		this.canvasbordercolor = canvasbordercolor;
	}
	public String getCanvasborderthickness() {
		return canvasborderthickness;
	}
	public void setCanvasborderthickness(String canvasborderthickness) {
		this.canvasborderthickness = canvasborderthickness;
	}
	/**
	public String getYaxismaxvalue() {
		return yaxismaxvalue;
	}
	public void setYaxismaxvalue(String yaxismaxvalue) {
		this.yaxismaxvalue = yaxismaxvalue;
	}
	*/
	public String getCaptionpadding() {
		return captionpadding;
	}
	public void setCaptionpadding(String captionpadding) {
		this.captionpadding = captionpadding;
	}
	public String getLinethickness() {
		return linethickness;
	}
	public void setLinethickness(String linethickness) {
		this.linethickness = linethickness;
	}
	public String getYaxisvaluespadding() {
		return yaxisvaluespadding;
	}
	public void setYaxisvaluespadding(String yaxisvaluespadding) {
		this.yaxisvaluespadding = yaxisvaluespadding;
	}
	public String getLegendshadow() {
		return legendshadow;
	}
	public void setLegendshadow(String legendshadow) {
		this.legendshadow = legendshadow;
	}
	public String getLegendborderalpha() {
		return legendborderalpha;
	}
	public void setLegendborderalpha(String legendborderalpha) {
		this.legendborderalpha = legendborderalpha;
	}
	public String getPalettecolors() {
		return palettecolors;
	}
	public void setPalettecolors(String palettecolors) {
		this.palettecolors = palettecolors;
	}
	public String getShowborder() {
		return showborder;
	}
	public void setShowborder(String showborder) {
		this.showborder = showborder;
	}
	
}
