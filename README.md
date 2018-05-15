# uri-parser
Uniform Resource Identifier Parser

Developed as a TDD kata experiment after I saw the following figure on this [Wikipedia article](https://en.wikipedia.org/wiki/Uniform_Resource_Identifier) (which is worth a read for more information on URIs and their syntax):

```
         userinfo      host      port
          ┌───┴──┐ ┌──────┴──────┐ ┌┴┐
  https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top
  └─┬─┘   └─────────────┬────────────┘└───────┬───────┘ └────────────┬────────────┘ └┬┘
 scheme             authority               path                   query         fragment

  ldap://[2001:db8::7]/c=GB?objectClass?one
  └─┬┘   └─────┬─────┘└─┬─┘ └──────┬──────┘
 scheme    authority  path       query

  mailto:John.Doe@example.com
  └──┬─┘ └─────────┬────────┘
  scheme         path

  news:comp.infosystems.www.servers.unix
  └─┬┘ └───────────────┬───────────────┘
 scheme              path

  tel:+1-816-555-1212
  └┬┘ └──────┬──────┘
scheme     path

  telnet://192.0.2.16:80/
  └──┬─┘   └─────┬─────┘│
  scheme     authority path

  urn:oasis:names:specification:docbook:dtd:xml:4.1.2
  └┬┘ └──────────────────────┬──────────────────────┘
scheme                     path

```

Given how clearly the structure of a URI is defined, I thought it would be interesting to write something that could (1) identify the various components and (2) produce similar figures for arbitrary URIs (rather than manually building them myself).

## Q: Where's the documentation?

### A: The code itself and the unit tests *are* the documentation. Also, so is this page right here.

I don't like lies, and documentation in the form of comments can't lie if it doesn't exist. Putting principles from Uncle Bob's *Clean Code* to practice, my aim is to make the code as clean and self documenting as possible.

As mentioned above, the initial motivation was to undertake a TDD exercise, and I 100% trust the tests. All classes emerged from the `URI.java` as extra responsibilites revealed themselves, so even though they may not be explicitly tested, they are implicitly.

## Q: So where do I start?

### A: I suggest starting at the test cases.

They test the behaviour I explicitly specified on class(es) that I made to be used. We can consider the rest of the codebase to be implementation details (but remember, that's where the devil is). They are entry points to the code for the machines, and for how I approached the problem.

## Q: How do I use this?

### A: In a JUnit test harness? honestly idk yet

The code currently parses URIs (and builds them from individual components) but it doesn't do much else. That should change as this continues to be developed.

I wrote this in IntelliJ and made use of the built in JUnit and Hamcrest libraries. Otherwise, ¯\\\_(ツ)_/¯
