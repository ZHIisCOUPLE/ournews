package com.ournews.front.service.impl;

import com.ournews.commons.persistence.BaseServiceImpl;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.NewsType;
import com.ournews.front.dao.IndexDao;
import com.ournews.front.service.IndexService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexServiceImpl extends BaseServiceImpl<NewsInfo, IndexDao> implements IndexService {

    //右侧小新闻
    @Value("${slideShow1}")
    private  Long slideShow1;
    @Value("${slideShow2}")
    private  Long slideShow2;
    @Value("${slideShow3}")
    private  Long slideShow3;
    @Value("${slideShow4}")
    private  Long slideShow4;


    //大新闻
    @Value("${bigNewsId}")
    private  Long bigNewsId;

    //底部小新闻
    @Value("${minNews1}")
    private  Long minNews1;
    @Value("${minNews2}")
    private  Long minNews2;
    @Value("${minNews3}")
    private  Long minNews3;
    @Value("${minNews4}")
    private  Long minNews4;

    @Override
    public List<NewsType> getNewsType() {
        return dao.getNewsTypeList();
    }


    @Override
    public String getNewsInfoPicUrl(Long id) {
        return dao.getNewsInfoPicUrl(id);
    }



    @Override
    public Map<String, List<NewsInfo>> getClassifyNewsInfo() {
        Map<String,List<NewsInfo>> map = new HashMap<>();
        //获取全部新闻
        List<NewsInfo> newsInfoList = dao.getList();
        //获取轮播新闻  根据ID
        List<NewsInfo> slideShowNews = new ArrayList<>();
        //获取大新闻
        List<NewsInfo> bigNewsList = new ArrayList<>();
        //获取右侧小新闻
        List<NewsInfo> rightMinNewsList = new ArrayList<>();
        //获取底部小新闻

        Iterator<NewsInfo> iterator = newsInfoList.iterator();
        while (iterator.hasNext()){
            NewsInfo newsInfo = iterator.next();
            boolean fla = true;
                //轮播新闻
            if (slideShowNews.size() < getSlideShowNewsId().length){
                for (Long bigNewsID : getSlideShowNewsId()) {
                    if (newsInfo.getId() == bigNewsID){
                        //相等
                        slideShowNews.add(newsInfo);
                        fla = false;
                        break;
                    }
                }
            }
            //大新闻
            if (bigNewsList.size() < 1 && newsInfo.getId() == bigNewsId){
                bigNewsList.add(newsInfo);
                fla = false;
            }
            //右侧小新闻
            if (fla && rightMinNewsList.size() < getRightMinNewsId().length){
                for (Long rightMinNewsId : getRightMinNewsId()) {
                    if (newsInfo.getId() == rightMinNewsId){
                        rightMinNewsList.add(newsInfo);
                        break;
                    }
                }
            }


        }
        //底部新闻 start=============================
        newsInfoList.removeAll(slideShowNews);
        newsInfoList.removeAll(bigNewsList);
        newsInfoList.removeAll(rightMinNewsList);
        //新闻类型 所有
        List<NewsType> allNewsType = dao.getNewsTypeList();
        //最终返回的底部list
        List<NewsInfo> tagList = new ArrayList<>();
        List<NewsInfo> source =null;
        for (NewsType newsType : allNewsType) {
            source = new ArrayList<>();
            for (NewsInfo newsInfo : newsInfoList) {
                if (newsInfo.getType().equals(newsType.getName())){
                    source.add(newsInfo);
                }
            }
            //根据时间排序  拿最新的那个
            if (source.size() >= 2){
                sort(source);
                tagList.add(source.get(0));
                tagList.add(source.get(1));
                newsInfoList.removeAll(source);
            }else if(source.size()>1){
                tagList.add(source.get(0));
            }
        }
        //底部新闻 end=============================
        sort(slideShowNews);
        sort(rightMinNewsList);
        map.put("SLIDESHOWNEWS",slideShowNews);
        map.put("BIGNEWS",bigNewsList);
        map.put("RIGHTMINNEWS",rightMinNewsList);
        map.put("FOOTMINNEWS",tagList);
        return map;
    }

    //打包轮播新闻ID
    private Long[] getSlideShowNewsId(){
        Long[] slideShowNewsId = new Long[]{slideShow1,slideShow2,slideShow3,slideShow4};
        return slideShowNewsId;
    }
    //打包右侧小新闻ID
    private Long[] getRightMinNewsId(){
        Long[] rightMinNewsId = new Long[]{minNews1,minNews2,minNews3,minNews4};
        return rightMinNewsId;
    }



    //根据时间排序
    private void sort(List<NewsInfo> list){
        Collections.sort(list, new Comparator<NewsInfo>() {
            @Override
            public int compare(NewsInfo o1, NewsInfo o2) {
                try {
                    long time1 = o1.getUpdated().getTime();
                    long time2 = o2.getUpdated().getTime();
                    if (time1 < time2) {
                        return 1;
                    } else if (time1 > time2) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

}
