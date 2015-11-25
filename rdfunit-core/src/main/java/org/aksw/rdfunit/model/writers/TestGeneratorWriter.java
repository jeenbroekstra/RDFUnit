package org.aksw.rdfunit.model.writers;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;
import org.aksw.rdfunit.model.interfaces.ResultAnnotation;
import org.aksw.rdfunit.model.interfaces.TestGenerator;
import org.aksw.rdfunit.vocabulary.RDFUNITv;

/**
 * Description
 *
 * @author Dimitris Kontokostas
 * @since 6/17/15 5:57 PM
 * @version $Id: $Id
 */
public final class TestGeneratorWriter implements ElementWriter {

    private final TestGenerator testGenerator;

    private TestGeneratorWriter(TestGenerator testGenerator) {
        this.testGenerator = testGenerator;
    }

    /**
     * <p>create.</p>
     *
     * @param testGenerator a {@link org.aksw.rdfunit.model.interfaces.TestGenerator} object.
     * @return a {@link org.aksw.rdfunit.model.writers.TestGeneratorWriter} object.
     */
    public static TestGeneratorWriter create(TestGenerator testGenerator) {return new TestGeneratorWriter(testGenerator);}

    /** {@inheritDoc} */
    @Override
    public Resource write(Model model) {
        Resource resource = ElementWriterUtils.copyElementResourceInModel(testGenerator, model);

        resource
                .addProperty(RDF.type, RDFUNITv.TestGenerator)
                .addProperty(DCTerms.description, testGenerator.getTAGDescription())
                .addProperty(RDFUNITv.sparqlGenerator, testGenerator.getTAGQuery())
                .addProperty(RDFUNITv.basedOnPattern, ElementWriterUtils.copyElementResourceInModel(testGenerator.getTAGPattern(), model));


        for (ResultAnnotation resultAnnotation: testGenerator.getTAGAnnotations()) {
            Resource annotationResource = ResultAnnotationWriter.create(resultAnnotation).write(model);
            resource.addProperty(RDFUNITv.resultAnnotation, annotationResource);
        }

        return resource;
    }
}
