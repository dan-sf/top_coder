"""
SRM 203
You are developing the matchmaking component of an online dating site.
Prospective members must fill out a questionnaire consisting of binary
questions such as Do you prefer to vacation (a) in the mountains or (b) at the
seaside? and Would you rather travel (a) by plane or (b) by train?

You are to match up men with women by maximizing the number of answers each
couple has in common. A man and a woman have an answer in common whenever they
give the same answer to the same question. Conflicts can easily arise due to
numerical ties, but you will be able to resolve all such conflicts using the
following procedure. Note that there will be equal numbers of men and women,
with names being unique in each sex.

Take the woman whose name comes earliest in lexicographic order, and consider
the men with whom she has the greatest number of answers in common. Among these
men, pick the one whose name comes earliest in lexicographic order. You have
found the woman's best match. Remove this couple from the dating pool, and
repeat the matching procedure until there are no more singles left.

You are given a String[], namesWomen, containing the names of single women, and
another String[], answersWomen, containing their answers. The kth element of
answersWomen lists the answers of the woman whose name is the kth element of
namesWomen. If there are n questions in the questionnaire, then every element
of answersWomen consists of n characters, each of which is either 'a' or 'b'.
The answers are always given in the fixed questionnaire order. You are
similarly given the String[]s namesMen and answersMen for the single men.
Lastly, you are given a String, queryWoman, containing the name of a woman.
Return the name of the man to whom she is matched after you have formed all
couples according to the above rules.
"""

class MatchMaking(object):
    def makeMatch(self, namesWomen, answersWomen, namesMen, answersMen, queryWomen):

        men_score = {}
        women_score = {}

        # Create m/w score dicts
        women_score = self.create_score_dict(namesWomen,answersWomen)
        men_score = self.create_score_dict(namesMen,answersMen)

        # Create sorted lists of m/w
        women_name_sort = women_score.keys()
        women_name_sort.sort()
        men_name_sort = men_score.keys()
        men_name_sort.sort()

        # Create matches
        matches = self.create_matches(women_name_sort, men_name_sort, men_score, women_score)

        return matches[queryWomen]

    def create_score_dict(self, names, answers):
        # Create the score dict "NAME": SCORE
        # Replacing a with 1 and b with 0
        output = {}
        answer_map = { 'a': 1, 'b': 0 }
        for name, score in zip(names, answers):
            output[name] = [ answer_map[x] for x in list(score) ]
        return output

    def create_matches(self, women_name_sort, men_name_sort, men_score, women_score):
        # Loop through all the women to create each man match
        # Once a man match is made, remove that man from the
        # pool of possible matches
        matches = {}
        for women in women_name_sort:
            # Create sorted women match scores
            women_match_score = []
            for men in men_name_sort:
                women_match_score.append([ self.get_equal(men_aws, women_aws) for men_aws, women_aws in zip(men_score[men], women_score[women]) ])

            # Get matching men index for the women
            match_index = self.get_index(women_match_score)
            matches[women] = men_name_sort[match_index]

            # Remove the men from the choices
            men_name_sort.remove(men_name_sort[match_index])

        return matches

    def get_equal(self, a, b):
        if a == b:
            return 1
        else:
            return 0

    def get_index(self, match_score):
        index = None
        largest = -1
        for i,m in enumerate(match_score):
            if sum(m) > largest:
                largest = sum(m)
                index = i
        return index

if __name__ == '__main__':
    m = MatchMaking()

    print m.makeMatch(["Constance", "Bertha", "Alice"],["aaba", "baab", "aaaa"],["Chip", "Biff", "Abe"],["bbaa", "baaa", "aaab"],"Bertha")
    # Returns: "Biff"

    print m.makeMatch(["Constance", "Bertha", "Alice"], ["aaba", "baab", "aaaa"], ["Chip", "Biff", "Abe"], ["bbaa", "baaa", "aaab"], "Constance")
    # Returns: "Chip"

    print m.makeMatch(["Constance", "Alice", "Bertha", "Delilah", "Emily"], ["baabaa", "ababab", "aaabbb", "bababa", "baabba"], ["Ed", "Duff", "Chip", "Abe", "Biff"], ["aabaab", "babbab", "bbbaaa", "abbbba", "abaaba"], "Constance")
    # Returns: "Duff"

    print m.makeMatch(["Constance", "Alice", "Bertha", "Delilah", "Emily"], ["baabaa", "ababab", "aaabbb", "bababa", "baabba"], ["Ed", "Duff", "Chip", "Abe", "Biff"], ["aabaab", "babbab", "bbbaaa", "abbbba", "abaaba"], "Delilah")
    # Returns: "Chip"

    print m.makeMatch(["Constance", "Alice", "Bertha", "Delilah", "Emily"], ["baabaa", "ababab", "aaabbb", "bababa", "baabba"], ["Ed", "Duff", "Chip", "Abe", "Biff"], ["aabaab", "babbab", "bbbaaa", "abbbba", "abaaba"], "Emily")
    # Returns: "Ed"

    print m.makeMatch(["anne", "Zoe"], ["a", "a"], ["bob", "chuck"], ["a", "a"], "Zoe")
    # Returns: "bob"

    print m.makeMatch(["F", "M", "S", "h", "q", "g", "r", "N", "U", "x", "H", "P", "o", "E", "R", "z", "L", "m", "e", "u", "K", "A", "w", "Q", "O", "v", "j", "a", "t", "p", "C", "G", "k", "c", "V", "B", "D", "s", "n", "i", "f", "T", "I", "l", "d", "J", "y", "b"], ["abaabbbb", "bbaabbbb", "aaabaaab", "aabbaaaa", "baabbaab", "aaababba", "bbabbbbb", "bbbabbba", "aaabbbba", "aabbbaaa", "abbabaaa", "babbabbb", "aaaaabba", "aaaabbaa", "abbbabaa", "babababa", "abbaaaaa", "bbababba", "baaaaaba", "baaaaabb", "bbbbabba", "ababbaaa", "abbbabab", "baabbbaa", "bbbaabbb", "aababbab", "ababbabb", "abbaabba", "baabbabb", "aaabaaab", "aabbbaba", "aabaaabb", "abababba", "aabbaaaa", "aabbabaa", "bababaaa", "aabaaaab", "bbbbaabb", "baaababb", "abaabbab", "aabbbaaa", "baabbaba", "bbabbbaa", "aabbbbaa", "abbbaaab", "abababbb", "ababaaba", "bababaaa"], ["f", "C", "v", "g", "Q", "z", "n", "c", "B", "o", "M", "F", "u", "x", "I", "T", "K", "L", "E", "U", "w", "A", "d", "t", "e", "R", "D", "s", "p", "q", "m", "r", "H", "j", "J", "V", "l", "a", "k", "h", "G", "y", "i", "P", "O", "N", "b", "S"], ["bbbaabab", "bbabaabb", "ababbbbb", "bbbababb", "baababaa", "bbaaabab", "abbabbaa", "bbbabbbb", "aabbabab", "abbababa", "aababbbb", "bababaab", "aaababbb", "baabbaba", "abaaaaab", "bbaababa", "babaabab", "abbabbba", "ababbbab", "baabbbab", "babbaaab", "abbbbaba", "bbabbbba", "baaabaab", "ababbabb", "abbbaabb", "bbbbaabb", "bbbaaabb", "baabbaba", "bbabaaab", "aabbbaab", "abbbbabb", "bbaaaaba", "bbbababa", "abbaabba", "bababbbb", "aabaaabb", "babbabab", "baaaabaa", "ababbaba", "aaabaabb", "bbaaabaa", "baaaaabb", "bbaabaab", "bbababab", "aabaaaab", "aaaaabab", "aabbaaba"], "U")
    # Returns: "x"

    print m.makeMatch(["q", "M", "w", "y", "p", "N", "s", "r", "a", "H", "o", "n", "F", "m", "l", "b", "D", "j", "C", "u", "f", "I", "g", "L", "i", "x", "A", "G", "O", "k", "h", "d", "c", "E", "B", "v", "J", "z", "K", "e", "t"], ["aabbaaabb", "baabababb", "bbaababba", "bbbaaaaaa", "abaaaabaa", "bababbbab", "abbaabbaa", "aabababbb", "bababaaaa", "abbababaa", "aabbbbbba", "bbabbabab", "babaabbba", "babbabbbb", "baaabbbbb", "baaabaaaa", "aaabbaaab", "abbaabbbb", "abbabbbab", "bbaaaabba", "babbaaabb", "aabbabbab", "baaababba", "ababaabab", "bbbaabbab", "aaaabbabb", "babaaaaaa", "abbbbaaab", "aabaaabba", "bbbaaaaba", "bbbbbbaab", "aabbaaabb", "aabaabbab", "aababaaba", "bbabbbbab", "abbabaaab", "babaaabbb", "bababbaaa", "aabbaabaa", "baaabbabb", "bbbbbbbbb"], ["m", "k", "n", "q", "L", "E", "M", "l", "w", "x", "g", "e", "i", "z", "F", "r", "a", "h", "f", "D", "J", "K", "j", "v", "A", "t", "N", "y", "s", "c", "o", "p", "d", "b", "B", "G", "O", "I", "u", "C", "H"], ["bbaaabbba", "bbaaaaaab", "abaaababb", "baaaabbbb", "abbbababa", "baaaaaaaa", "aabbbbbab", "aaaaabbba", "baabababb", "babaaabab", "baaababaa", "bbbbaabba", "bbaabbabb", "bbaaababb", "abbabbaba", "aababaaab", "abbbbbbaa", "aabbaabaa", "bbbaabbba", "abbabbaba", "aaabbbaaa", "bbaabaaaa", "aabababbb", "abbbbabab", "baaabbbba", "bababbbba", "aababbaab", "bbaabbaab", "bbbaaabbb", "babbbbabb", "ababababb", "babaaabab", "bbaaaaaba", "aaaaabaaa", "abbaaabbb", "bbbbababb", "baabababb", "bbaabaaaa", "aaababbbb", "abbbbbbba", "bbaabbaaa"], "o")
    # Returns: "C"

