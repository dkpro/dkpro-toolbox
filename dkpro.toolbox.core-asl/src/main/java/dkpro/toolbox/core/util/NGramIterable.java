/*******************************************************************************
 * Copyright 2011
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package dkpro.toolbox.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import dkpro.toolbox.core.NGram;

/**
 * Creates a NGram iterable from a list of items.
 * It does not detect any sentence (or other) boundaries.
 * Thus, one should make sure to only add lists that reflect a sentence or a phrase (or similar).
 *
 * @author zesch
 * @param <T>
 *
 */
public class NGramIterable<T> implements Iterable<NGram<T>>
{
	List<NGram<T>> nGramList;

	public NGramIterable(Iterable<T> items, int minN, int maxN)
	{
		this.nGramList = createNGramList(items, minN, maxN);
	}

    public NGramIterable(T[] items, int minN, int maxN)
    {
        this.nGramList = createNGramList(Arrays.asList(items), minN, maxN);
    }


	@Override
	public Iterator<NGram<T>> iterator()
	{
		return nGramList.iterator();
	}

	private List<NGram<T>> createNGramList(Iterable<T> items, int minN, int maxN)
	{
        if (minN > maxN) {
            throw new IllegalArgumentException("minN needs to be smaller or equal than maxN.");
        }

		List<NGram<T>> nGrams = new ArrayList<NGram<T>>();

		// fill item list
		List<T> itemList = new ArrayList<T>();
		for (T t : items) {
			itemList.add(t);
		}

		for (int k = minN; k <= maxN; k++) {
			// if the number of items is less than k => break
			if (itemList.size() < k) {
				break;
			}
			nGrams.addAll(getNGrams(itemList, k));
		}

		return nGrams;
	}

	private List<NGram<T>> getNGrams(List<T> itemList, int k)
	{
		List<NGram<T>> nGrams = new ArrayList<NGram<T>>();

		int size = itemList.size();
		for (int i = 0; i < (size + 1 - k); i++) {
		    List<T> parts = new ArrayList<T>();
		    for (int j=0; j<k; j++) {
		        parts.add(itemList.get(i+j));
		    }
		    nGrams.add(new NGram<T>(parts));
		}

		return nGrams;
	}
}