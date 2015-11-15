package org.chaos.fx.cnbeta.net;

import org.chaos.fx.cnbeta.net.model.ArticleSummary;
import org.chaos.fx.cnbeta.net.model.Comment;
import org.chaos.fx.cnbeta.net.model.HotComment;
import org.chaos.fx.cnbeta.net.model.NewsContent;
import org.chaos.fx.cnbeta.net.model.Topic;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @author Chaos
 *         2015/11/03.
 */
public class CnBetaApiHelper {

    private static CnBetaApi sCnBetaApi;

    public static void initialize() {
        sCnBetaApi = new Retrofit.Builder()
                .baseUrl(CnBetaApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CnBetaApi.class);
    }

    public static Call<CnBetaApi.Result<List<ArticleSummary>>> articles() {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.articles(timestamp, CnBetaSignUtil.articlesSign(timestamp));
    }

    public static Call<CnBetaApi.Result<List<ArticleSummary>>> topicArticles(String topicId) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.topicArticles(
                timestamp,
                CnBetaSignUtil.topicArticlesSign(timestamp, topicId),
                topicId);
    }

    public static Call<CnBetaApi.Result<List<ArticleSummary>>> newArticles(String topicId, int startSid) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.newArticles(
                timestamp,
                CnBetaSignUtil.newArticlesSign(timestamp, topicId, startSid),
                topicId,
                startSid);
    }

    public static Call<CnBetaApi.Result<List<ArticleSummary>>> oldArticles(String topicId,
                                                                           int endSid) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.oldArticles(
                timestamp,
                CnBetaSignUtil.oldArticlesSign(timestamp, topicId, endSid),
                topicId,
                endSid);
    }

    public static Call<CnBetaApi.Result<NewsContent>> articleContent(int sid) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.articleContent(
                timestamp,
                CnBetaSignUtil.articleContentSign(timestamp, sid),
                sid);
    }

    public static Call<CnBetaApi.Result<List<Comment>>> comments(int sid,
                                                                 int page) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.comments(
                timestamp,
                CnBetaSignUtil.commentsSign(timestamp, sid, page),
                sid,
                page);
    }

    public static Call<CnBetaApi.Result<Object>> addComment(int sid,
                                                            String content) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.addComment(
                timestamp,
                CnBetaSignUtil.addCommentSign(timestamp, sid, content),
                sid,
                content);
    }

    public static Call<CnBetaApi.Result<Object>> replyComment(int sid,
                                                              int pid,
                                                              String content) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.replyComment(
                timestamp,
                CnBetaSignUtil.replyCommentSign(timestamp, sid, pid, content),
                sid,
                pid,
                content);
    }

    public static Call<CnBetaApi.Result<String>> supportComment(int sid) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.supportComment(
                timestamp,
                CnBetaSignUtil.supportCommentSign(timestamp, sid),
                sid);
    }

    public static Call<CnBetaApi.Result<String>> againstComment(int sid) {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.againstComment(
                timestamp,
                CnBetaSignUtil.againstCommentSign(timestamp, sid),
                sid);
    }

    public static Call<CnBetaApi.Result<List<HotComment>>> hotComment() {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.hotComment(timestamp, CnBetaSignUtil.hotCommentSign(timestamp));
    }

    private static String getTypeString(int type) {
        return type == CnBetaApi.TYPE_COMMENTS ? "comments" : type == CnBetaApi.TYPE_COUNTER ? "counter" : "dig";
    }

    public static Call<CnBetaApi.Result<List<ArticleSummary>>> todayRank(int type) {
        long timestamp = System.currentTimeMillis();
        String typeStr = getTypeString(type);
        return sCnBetaApi.todayRank(
                timestamp,
                CnBetaSignUtil.todayRankSign(timestamp, typeStr),
                typeStr);
    }

    public static Call<CnBetaApi.Result<List<ArticleSummary>>> top10() {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.top10(timestamp, CnBetaSignUtil.top10Sign(timestamp));
    }

    public static Call<CnBetaApi.Result<List<Topic>>> topics() {
        long timestamp = System.currentTimeMillis();
        return sCnBetaApi.topics(timestamp, CnBetaSignUtil.topicsSign(timestamp));
    }
}