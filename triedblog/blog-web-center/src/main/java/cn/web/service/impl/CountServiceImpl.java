package cn.web.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import cn.commons.common.PublicResultJosn;
import cn.web.dao.DayAreaTimesStatMapper;
import cn.web.dao.DayBrowserTimesStatMapper;
import cn.web.dao.DayOsTimesStatMapper;
import cn.web.dto.StatQueryVo;
import cn.web.entity.DayAreaTimesStat;
import cn.web.entity.DayBrowserTimesStat;
import cn.web.entity.DayOsTimesStat;
import cn.web.example.DayAreaTimesStatExample;
import cn.web.example.DayBrowserTimesStatExample;
import cn.web.example.DayOsTimesStatExample;
import cn.web.example.DayAreaTimesStatExample.Criteria;
import cn.web.service.CountService;
import cn.web.utils.StatUtils;

@Service
public class CountServiceImpl implements CountService {

	@Autowired
	private DayAreaTimesStatMapper areaTimesStatMapper;

	@Autowired
	private DayBrowserTimesStatMapper browserTimesStatMapper;

	@Autowired
	private DayOsTimesStatMapper osTimesStatMapper;

	@Override
	public PublicResultJosn selectAreaByExample(StatQueryVo queryVo) {
		DayAreaTimesStatExample example = new DayAreaTimesStatExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryVo.getStart())) {
			criteria.andDayGreaterThanOrEqualTo(queryVo.getStart());
		}
		if (StringUtils.isNoneBlank(queryVo.getEnd())) {
			criteria.andDayLessThanOrEqualTo(queryVo.getEnd());
		}
		List<DayAreaTimesStat> list = areaTimesStatMapper.selectByExample(example);
		HashMap<String, Long> map = StatUtils.ParseAreaState(list);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), map);
	}

	@Override
	public PublicResultJosn selectOsByExample(StatQueryVo queryVo) {
		DayOsTimesStatExample example = new DayOsTimesStatExample();
		cn.web.example.DayOsTimesStatExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryVo.getStart())) {
			criteria.andDayGreaterThanOrEqualTo(queryVo.getStart());
		}
		if (StringUtils.isNoneBlank(queryVo.getEnd())) {
			criteria.andDayLessThanOrEqualTo(queryVo.getEnd());
		}
		List<DayOsTimesStat> list = osTimesStatMapper.selectByExample(example);
		HashMap<String, Long> map = StatUtils.ParseOsState(list);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), map);

	}

	@Override
	public PublicResultJosn selectBrowserByExample(StatQueryVo queryVo) {
		DayBrowserTimesStatExample example = new DayBrowserTimesStatExample();
		cn.web.example.DayBrowserTimesStatExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(queryVo.getStart())) {
			criteria.andDayGreaterThanOrEqualTo(queryVo.getStart());
		}
		if (StringUtils.isNoneBlank(queryVo.getEnd())) {
			criteria.andDayLessThanOrEqualTo(queryVo.getEnd());
		}
		List<DayBrowserTimesStat> list = browserTimesStatMapper.selectByExample(example);
		java.util.HashMap<String, Long> map = StatUtils.ParseBrowserState(list);
		return new PublicResultJosn(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), map);
	}

}
