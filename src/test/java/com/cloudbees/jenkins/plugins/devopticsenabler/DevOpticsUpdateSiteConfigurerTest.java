/*
 * The MIT License
 *
 * Copyright (c) 2018, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cloudbees.jenkins.plugins.devopticsenabler;

import hudson.model.UpdateCenter;
import hudson.model.UpdateSite;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link DevOpticsUpdateSiteConfigurer}.
 */
public final class DevOpticsUpdateSiteConfigurerTest {
    @Rule
    public final JenkinsRule rule = new JenkinsRule() {
        @Override
        protected void configureUpdateCenter() throws Exception {
            // Do not overwrite anything
        }
    };

    @Test
    public void configured() {
        final List<UpdateSite> sites;
        final UpdateCenter updateCenter = rule.jenkins.getUpdateCenter();
        synchronized (updateCenter) {
            sites = new ArrayList<>(updateCenter.getSites());
        }
        Assert.assertTrue("Update site configured", sites.stream().anyMatch(s -> s instanceof DevOpticsUpdateSite));
    }

}
