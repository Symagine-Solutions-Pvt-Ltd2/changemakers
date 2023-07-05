package com.lms.changemaker;


import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * This matcher will return all matches which match <cdoe>baselineMatches</code>, if
 * and only if, those matches are not matched by <code>ignoreMatches</code>.
 * </p>
 * 
 * <p>
 * This matcher first checks <code>ignoreMatches</code>. If a given request is found
 * as a match to <code>ignoreMatches</code>, this matcher will return false (not a match).
 * If a given request does not match <code>ignoreMatches</code>, then this matcher returns
 * whether or not that request matches <code>baselineMatches</code>.
 * </p>
 * 
 * Effectively:<br>
 * <code>
 * if (ignoreMatches.matches(request)) {
 *      return false;
 * } else {
 *      return baselineMatches.matches(request);
 * }
 * </code>
 * @param baselineMatches Matcher used to determine a request match.
 * @param ignoreMatches Matcher used to exclude matches from the baselineMatcher.
 */
/*
 * public class AExceptBRequestMatcher implements RequestMatcher { private
 * RequestMatcher baselineMatches; private RequestMatcher ignoreMatches;
 * 
 * public AExceptBRequestMatcher(String baselineMatches, RequestMatcher
 * ignoreMatches) { this(new AntPathRequestMatcher(baselineMatches),
 * ignoreMatches); }
 * 
 * public AExceptBRequestMatcher(RequestMatcher baselineMatches, RequestMatcher
 * ignoreMatches) { this.baselineMatches = baselineMatches; this.ignoreMatches =
 * ignoreMatches; }
 * 
 * @Override public boolean matches(HttpServletRequest request) { if
 * (ignoreMatches.matches(request)) { return false; } else { return
 * baselineMatches.matches(request); } } }
 */