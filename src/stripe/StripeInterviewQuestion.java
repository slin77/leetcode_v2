package stripe;


//Your previous Plain Text content is preserved below:

// Step 1)
//
// Imagine an Airbnb-like vacation rental service, where users in different cities can exchange their apartment with
// another user for a week. Each user compiles a wishlist of the apartments they like. These wishlists are ordered,
// so the top apartment on a wishlist is that user's first choice for where they would like to spend a vacation.
// You will be asked to write part of the code that will help an algorithm find pairs of users who would like to
// swap with each other.
//
// Given a set of users, each with an *ordered* wishlist of other users' apartments:
//
// a's wishlist: c d
// b's wishlist: d a c
// c's wishlist: a b
// d's wishlist: c a b
//
// The first user in each wishlist is the user's first-choice for whose apartment they would like to swap into.
// Write a function called hasMutualFirstChoice() which takes a username and returns true if that user and
// another user are each other's first choice, and otherwise returns false.
//
// hasMutualFirstChoice('a') // true (a and c)
// hasMutualFirstChoice('b') // false (b's first choice does not *mutually* consider b as their first choice)
//
// Then expand the base case beyond just "first" choices, to include all "mutually ranked choices". Write another
// function which takes a username and an option called "rank" to indicate the wishlist rank to query on. If given
// a rank of 0, you should check for a first choice pair, as before. If given 1, you should check for a pair of
// users who are each others' second-choice. Call your new function hasMutualPairForRank() and when done,
// refactor hasMutualFirstChoice() to depend on your new function.
//
// hasMutualPairForRank('a', 0) // true (a and c)
// hasMutualPairForRank('a', 1) // true (a and d are mutually each others' second-choice)


import java.io.*;
import java.util.*;

class Tests {
    public static Map<String, String[]> data () {
        Map<String, String[]> data = new HashMap<String, String[]>();
        data.put("a", new String[] {"c", "d"});
        data.put("b", new String[] {"d", "a", "c"});
        data.put("c", new String[] {"a", "b"});
        data.put("d", new String[] {"c", "a", "b"});
        data.put("e", new String[] {"f", "a"});
        data.put("f", new String[] {"e"});
        return data;
    }

    public static void assertEqual(boolean actual, boolean expected) {
        if (expected == actual) {
            System.out.println("PASSED");
        } else {
            throw new AssertionError(
                    "Expected:\n  " + expected +
                            "\nActual:\n  " + actual +
                            "\n");
        }
    }

    public static void assertEqual(String[] actual, String expected[]) {
        if (!String.join(",", expected).equals(String.join(",", actual))) {
            throw new AssertionError(
                    "Expected:\n  " + String.join(",", expected) +
                            "\nActual:\n  " + String.join(",", actual) +
                            "\n");
        }
        System.out.println("PASSED");
    }

    public static void testHasMutualFirstChoice() {
        assertEqual(new Solution(data()).hasMutualFirstChoice("a"), true);
        assertEqual(new Solution(data()).hasMutualFirstChoice("b"), false);
    }

    public static void testHasMutualPairForRank() {
        assertEqual(new Solution(data()).hasMutualPairForRank("a", 0), true);
        assertEqual(new Solution(data()).hasMutualPairForRank("a", 1), true);
    }

    // Step 2)
//
// Every wishlist entry in the network is either "mutually ranked" or "not mutually ranked" depending on
// the rank the other user gives that user's apartment in return.
//
// The most common operation in the network is incrementing the rank of a single wishlist entry on a single
// user. This swaps the entry with the entry above it in that user's list. Imagine that, when this occurs,
// the system must recompute the "mutually-ranked-ness" of any pairings that may have changed.
//
// Write a function that takes a username and a rank representing the entry whose rank is being bumped up.
// Return an array of the users whose pairings with the given user *would* gain or lose mutually-ranked
// status as a result of the change, if it were to take place. Call your function changedPairings()
//
// a's wishlist: c d
// b's wishlist: d a c
// c's wishlist: a b
// d's wishlist: c a b
// e: f, b, c
// f: e, a
//
// // if d's second choice becomes their first choice, a and d will no longer be a mutually ranked pair
// changedPairings('d', 1) // returns ['a']
//
// // if b's third choice becomes their second choice, c and b will become a mutually ranked pair (mutual
// second-choices)
// changedPairings('b', 2) // returns ['c']
//
// // if b's second choice becomes their first choice, no mutually-ranked pairings are affected
// changedPairings('b', 1) // returns []
//


    public static void testChangedPairings () {
        // if d's second choice becomes their first choice, a and d
        // will no longer be a mutually ranked pair
        assertEqual(new Solution(data()).changedPairings("d", 1), new String[]{"a"});

        // if b's third choice becomes their second choice, c and b
        // will become a mutually ranked pair (mutual second-choices)
        assertEqual(new Solution(data()).changedPairings("b", 2), new String[]{"c"});

        // if b's second choice becomes their first choice, no
        // mutually-ranked pairings are affected
        assertEqual(new Solution(data()).changedPairings("b", 1), new String[]{});

        assertEqual(new Solution(data()).changedPairings("e", 1), new String[]{"f"});
    }

    // Step 3)
//
// A user's last choice is the last entry on their wishlist. Their second-to-last choice is second to last on
// their wishlist. This can be continued to define third-to-last choice, and so on, always counting from the
// end of the user's list of apartments.
//
// A mutually-ranked-anti-pairing is one where both parties rank each other's apartments identically near to
// (or far from) the *end* of each of their wishlists.
//
// Implement changedAntipairings(username, rank) to return an array of the users whose pairings with the
// given user either gain or lose mutually-ranked-anti-pairing status as a result of the change.   Note that,
// as before, the username and rank passed in identify the entry whose rank is being bumped up, so (a, 1)
// would refer to a's second-choice.
//
// a's wishlist: c d
// b's wishlist: d a c
// c's wishlist: a b
// d's wishlist: c a b
//
// // if b's third choice becomes their second choice, b and c will no longer be a mutually-ranked anti-pairing
// changedAntipairings('b', 2) // returns ['c']
//
// // if a's second choice becomes their first choice, a and c will be no longer be a mutually ranked anti-pairing
// // in addition, a and d will become a mutually ranked anti-pairing (the second-to-last choice of each other)
// changedAntipairings('a', 1) // returns ['c', 'd']

    public static void testChangedAntiPairings () {
        // if b's third choice becomes their second choice,
        // b and c will no longer be a mutually-ranked anti-pairing
        assertEqual(new Solution(data()).changedAntiPairings("b", 2), new String[]{"c"});

        // if a's second choice becomes their first choice,
        // a and c will be no longer be a mutually ranked anti-pairing
        // in addition, a and d will become a mutually ranked anti-pairing
        // (the second-to-last choice of each other)
        assertEqual(new Solution(data()).changedAntiPairings("a", 1), new String[]{"c", "d"});
    }

}

class Solution {
    Map<String, String[]> data;
    public Solution(Map<String, String[]> data) {
        this.data = data;
    }

    public boolean hasMutualFirstChoice(String username) {
       return hasMutualPairForRank(username, 0);
    }

    public boolean hasMutualPairForRank(String username, int rank) {
        if (rank < 0) return false;
        if (username == null || username.length()  == 0) return false;
        String[] userList= data.get(username);
        if (userList == null || userList.length <= rank) return false;
        String otherUser = userList[rank];
        String[] otherList = data.get(otherUser);
        if (otherList == null || otherList.length <= rank) return false;
        return otherList[rank].equals(username);
    }

    public String[] changedPairings(String username, int rank) {
        if (username == null || username.length()  == 0) return new String[]{};
        Set<String> res = new HashSet<>();
        String[] userList = data.get(username);
        // if there was match for rank
        if (hasMutualPairForRank(username, rank)) {
            res.add(userList[rank]);
        }
        if (hasMutualPairForRank(username, rank - 1)) {
            res.add(userList[rank - 1]);
        }

        // now we do swap
        String temp = userList[rank - 1];
        userList[rank - 1] = userList[rank];
        userList[rank] = temp;

        if (hasMutualPairForRank(username, rank)) {
            res.add(userList[rank]);
        }
        if (hasMutualPairForRank(username, rank - 1)) {
            res.add(userList[rank - 1]);
        }

        String[] out = new String[res.size()];
        return res.toArray(out);
    }

    public String[] changedAntiPairings(String username, int rank) {
        if (username == null || username.length()  == 0) return new String[]{};
        Set<String> res = new HashSet<>();
        String[] userList = data.get(username);
        // if there was match for rank
        if (hasMutualAntiPairForRank(username, rank)) {
            res.add(userList[rank]);
        }
        if (hasMutualAntiPairForRank(username, rank - 1)) {
            res.add(userList[rank - 1]);
        }

        // now we do swap
        String temp = userList[rank - 1];
        userList[rank - 1] = userList[rank];
        userList[rank] = temp;

        if (hasMutualAntiPairForRank(username, rank)) {
            res.add(userList[rank]);
        }
        if (hasMutualAntiPairForRank(username, rank - 1)) {
            res.add(userList[rank - 1]);
        }

        String[] out = new String[res.size()];
        return res.toArray(out);
    }

    public boolean hasMutualAntiPairForRank(String username, int rank) {
        if (rank < 0) return false;
        if (username == null || username.length()  == 0) return false;
        String[] userList= data.get(username);
        String[] otherList = data.get(userList[rank]);

        int relativeIdx = userList.length - rank - 1;
        int otherIdx = otherList.length - 1 - relativeIdx;
        if (otherList == null || otherList.length <= otherIdx) return false;
        return otherList[otherIdx].equals(username);
    }

    public static void main(String[] args) {
        Tests.testHasMutualFirstChoice();
        Tests.testHasMutualPairForRank();
        Tests.testChangedPairings();
        Tests.testChangedAntiPairings();
    }
}
