package org.aksw.rdfunit.model.writers;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import org.aksw.rdfunit.model.impl.PatternBasedTestCaseImpl;
import org.aksw.rdfunit.model.interfaces.Binding;
import org.aksw.rdfunit.vocabulary.RDFUNITv;

/**
 * Description
 *
 * @author Dimitris Kontokostas
 * @since 6/17/15 5:57 PM
 * @version $Id: $Id
 */
final class PatternBasedTestCaseWriter implements ElementWriter {

    private final PatternBasedTestCaseImpl patternBasedTestCase;

    private PatternBasedTestCaseWriter(PatternBasedTestCaseImpl patternBasedTestCase) {
        this.patternBasedTestCase = patternBasedTestCase;
    }

    /**
     * <p>create.</p>
     *
     * @param patternBasedTestCase a {@link org.aksw.rdfunit.model.impl.PatternBasedTestCaseImpl} object.
     * @return a {@link org.aksw.rdfunit.model.writers.PatternBasedTestCaseWriter} object.
     */
    public static PatternBasedTestCaseWriter create(PatternBasedTestCaseImpl patternBasedTestCase) {return new PatternBasedTestCaseWriter(patternBasedTestCase);}

    /** {@inheritDoc} */
    @Override
    public Resource write(Model model) {
        Resource resource = ElementWriterUtils.copyElementResourceInModel(patternBasedTestCase, model);

        resource
                //.addProperty(RDFS.comment, "FOR DEBUGGING ONLY: SPARQL Query: \n" + new QueryGenerationSelectFactory().getSparqlQueryAsString(this) + "\n Prevalence SPARQL Query :\n" + getSparqlPrevalence());
                .addProperty(RDF.type, RDFUNITv.PatternBasedTestCase)
                .addProperty(RDFUNITv.basedOnPattern,  ElementWriterUtils.copyElementResourceInModel(patternBasedTestCase.getPattern(), model));

        for (Binding binding : patternBasedTestCase.getBindings()) {
            Resource bindingResource = BindingWriter.create(binding).write(model);
            resource.addProperty(RDFUNITv.binding, bindingResource);
        }

        TestAnnotationWriter.create(patternBasedTestCase.getTestCaseAnnotation()).write(model);


        return resource;
    }
}
