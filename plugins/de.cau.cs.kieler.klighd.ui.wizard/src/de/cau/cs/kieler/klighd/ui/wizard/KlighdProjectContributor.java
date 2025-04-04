/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013-2025 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.cau.cs.kieler.klighd.ui.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.ui.util.IProjectFactoryContributor;

/**
 * Class contains all contributions for a new KlighD project.
 * 
 * @author uru
 * @author chsch
 */
public class KlighdProjectContributor implements IProjectFactoryContributor {

    private KlighdProjectInfo projectInfo;

    public KlighdProjectContributor(final KlighdProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    @Override
    public void contributeFiles(final IProject project,
            final IProjectFactoryContributor.IFileCreator fileWriter) {
        this.contributePluginExtensions(fileWriter);
        this.contributeJDTprefs(fileWriter);
        boolean _isCreateXtendFile = this.projectInfo.isCreateXtendFile();
        if (_isCreateXtendFile) {
            this.contributeXtendTransformationFile(fileWriter);
        } else {
            this.contributeJavaTransformationFile(fileWriter);
        }
        boolean _isCreateMenuContribution = this.projectInfo.isCreateMenuContribution();
        if (_isCreateMenuContribution) {
            this.contributeCommandHandler(fileWriter);
            boolean _isUseFileEnding = this.projectInfo.isUseFileEnding();
            if (_isUseFileEnding) {
                this.contributeHandlerHelperClass(fileWriter);
            }
        }
    }

    /**
     * The generation of the JDT project preferences prevents any issues due to Eclipse'
     * incompatibility with Java 1.7.<br>
     * The content has simply been copied from an instance of plug-in project with "project specific
     * settings" using "compliance from execution environment 'J2SE-1.5' on the 'Java Build Path'".
     */
    private IFile contributeJDTprefs(final IFileCreator fileWriter) {

        String version = "11";
        switch (projectInfo.getExecutionEnvironment()) {
        case "JavaSE-1.6":
            version = "1.6";
            break;
        case "JavaSE-1.7":
            version = "1.7";
            break;
        case "JavaSE-1.8":
            version = "1.8";
            break;
        case "JavaSE-11":
            version = "11";
            break;

        }

        StringConcatenation _builder = new StringConcatenation();
        _builder.append("eclipse.preferences.version=1");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.codegen.inlineJsrBytecode=enabled");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.codegen.targetPlatform=");
        _builder.append(version);
        _builder.newLineIfNotEmpty();
        _builder.append("org.eclipse.jdt.core.compiler.codegen.unusedLocal=preserve");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.compliance=");
        _builder.append(version);
        _builder.newLineIfNotEmpty();
        _builder.append("org.eclipse.jdt.core.compiler.debug.lineNumber=generate");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.debug.localVariable=generate");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.debug.sourceFile=generate");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.problem.assertIdentifier=error");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.problem.enumIdentifier=error");
        _builder.newLine();
        _builder.append("org.eclipse.jdt.core.compiler.source=");
        _builder.append(version);
        _builder.newLineIfNotEmpty();
        return this.writeToFile(_builder, fileWriter,
                ((KlighdWizardSetup.SETTINGS_FOLDER + "/") + KlighdWizardSetup.JDT_PREFS_FILE));
    }

    private IFile contributeXtendTransformationFile(
            final IProjectFactoryContributor.IFileCreator fileWriter) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("package ");
        String _transformationPackage = this.projectInfo.getTransformationPackage();
        _builder.append(_transformationPackage);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("import com.google.inject.Inject");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.kgraph.KNode");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.krendering.KRenderingFactory");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions");
        _builder.newLine();
        _builder.append(
                "import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions");
        _builder.newLine();
        _builder.append(
                "import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import static de.cau.cs.kieler.klighd.syntheses.DiagramLayoutOptions.*");
        _builder.newLine();
        _builder.newLine();
        _builder.append(
                "import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import ");
        String _sourceModelClassFullyQualified =
                this.projectInfo.getSourceModelClassFullyQualified();
        _builder.append(_sourceModelClassFullyQualified);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("class ");
        String _transformationName = this.projectInfo.getTransformationName();
        _builder.append(_transformationName);
        _builder.append(" extends AbstractDiagramSynthesis<");
        String _sourceModelClassSimple = this.projectInfo.getSourceModelClassSimple();
        _builder.append(_sourceModelClassSimple);
        _builder.append("> {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KNodeExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KEdgeExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KPortExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KLabelExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KRenderingExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KContainerRenderingExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KPolylineExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("@Inject extension KColorExtensions");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("extension KRenderingFactory = KRenderingFactory.eINSTANCE");
        _builder.newLine();
        _builder.append("    ");
        _builder.newLine();
        _builder.append("    ");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("override KNode transform(");
        String _sourceModelClassSimple_1 = this.projectInfo.getSourceModelClassSimple();
        _builder.append(_sourceModelClassSimple_1, "    ");
        _builder.append(" model) {");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("val root = model.createNode().associateWith(model);");
        _builder.newLine();
        _builder.append("        ");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// Your dsl element <-> diagram figure mapping goes here!!");
        _builder.newLine();
        _builder.append("        ");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// Notice the statically imported classes \'DiagramSyntheses\' and \'DiagramLayoutOptions\'");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("//  that contribute direct access to a couple of (layout) configurations");
        _builder.newLine();
        _builder.append("        ");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("return root;");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        String _transformationPath = this.getTransformationPath();
        String _plus = (_transformationPath + ".xtend");
        return this.writeToFile(_builder, fileWriter, _plus);
    }

    private IFile contributeJavaTransformationFile(
            final IProjectFactoryContributor.IFileCreator fileWriter) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("package ");
        String _transformationPackage = this.projectInfo.getTransformationPackage();
        _builder.append(_transformationPackage);
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("import static de.cau.cs.kieler.klighd.syntheses.DiagramLayoutOptions.*;");
        _builder.newLine();
        _builder.append("import static de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.kgraph.KNode;");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.kgraph.util.KGraphUtil;");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis;");
        _builder.newLine();
        _builder.append("import ");
        String _sourceModelClassFullyQualified =
                this.projectInfo.getSourceModelClassFullyQualified();
        _builder.append(_sourceModelClassFullyQualified);
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("public class ");
        String _transformationName = this.projectInfo.getTransformationName();
        _builder.append(_transformationName);
        _builder.append(" extends AbstractDiagramSynthesis<");
        String _sourceModelClassSimple = this.projectInfo.getSourceModelClassSimple();
        _builder.append(_sourceModelClassSimple);
        _builder.append("> {");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("    ");
        _builder.append("public KNode transform(final ");
        String _sourceModelClassSimple_1 = this.projectInfo.getSourceModelClassSimple();
        _builder.append(_sourceModelClassSimple_1, "    ");
        _builder.append(" model) {");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("final KNode root = KGraphUtil.createInitializedNode();");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("associateWith(root, model);");
        _builder.newLine();
        _builder.append("        ");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// Your dsl element <-> diagram figure mapping goes here!!");
        _builder.newLine();
        _builder.append("        ");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// Notice the statically imported classes \'DiagramSyntheses\' and \'DiagramLayoutOptions\'");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("//  that contribute direct access to a couple of (layout) configurations");
        _builder.newLine();
        _builder.append("        ");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("return root;");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        String _transformationPath = this.getTransformationPath();
        String _plus = (_transformationPath + ".java");
        return this.writeToFile(_builder, fileWriter, _plus);
    }

    private String getTransformationPath() {
        String _replace = this.projectInfo.getTransformationPackage().replace(".", "/");
        String _plus = (KlighdWizardSetup.SRC_FOLDER + _replace);
        String _plus_1 = (_plus + "/");
        String _transformationName = this.projectInfo.getTransformationName();
        return (_plus_1 + _transformationName);
    }

    private IFile contributePluginExtensions(
            final IProjectFactoryContributor.IFileCreator fileWriter) {
        IFile _xblockexpression = null;
        {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            _builder.newLine();
            _builder.append("<?eclipse version=\"3.4\"?>");
            _builder.newLine();
            _builder.append("<plugin>");
            _builder.newLine();
            _builder.append("   ");
            _builder.append("<extension");
            _builder.newLine();
            _builder.append("         ");
            _builder.append("point=\"de.cau.cs.kieler.klighd.diagramSyntheses\">");
            _builder.newLine();
            _builder.append("   ");
            _builder.append("<diagramSynthesis");
            _builder.newLine();
            _builder.append("         ");
            _builder.append(
                    "class=\"de.cau.cs.kieler.klighd.syntheses.GuiceBasedSynthesisFactory:");
            String _transformationPackage = this.projectInfo.getTransformationPackage();
            String _plus = (_transformationPackage + ".");
            String _transformationName = this.projectInfo.getTransformationName();
            String _plus_1 = (_plus + _transformationName);
            _builder.append(_plus_1, "         ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("         ");
            _builder.append("id=\"");
            String _transformationPackage_1 = this.projectInfo.getTransformationPackage();
            String _plus_2 = (_transformationPackage_1 + ".");
            String _transformationName_1 = this.projectInfo.getTransformationName();
            String _plus_3 = (_plus_2 + _transformationName_1);
            _builder.append(_plus_3, "         ");
            _builder.append("\">");
            _builder.newLineIfNotEmpty();
            _builder.append("   ");
            _builder.append("</diagramSynthesis>");
            _builder.newLine();
            _builder.append("   ");
            _builder.append("</extension>");
            _builder.newLine();
            final String beginning = _builder.toString();
            String fileEndingCondition = "";
            String xtextEditorCondition = "";
            boolean _isUseFileEnding = this.projectInfo.isUseFileEnding();
            if (_isUseFileEnding) {
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("<adapt type=\"org.eclipse.core.resources.IResource\">");
                _builder_1.newLine();
                _builder_1.append("    ");
                _builder_1.append("<test");
                _builder_1.newLine();
                _builder_1.append("          ");
                _builder_1.append("forcePluginActivation=\"false\"");
                _builder_1.newLine();
                _builder_1.append("          ");
                _builder_1.append("property=\"org.eclipse.core.resources.extension\"");
                _builder_1.newLine();
                _builder_1.append("          ");
                _builder_1.append("value=\"");
                String _fileEnding = this.projectInfo.getFileEnding();
                _builder_1.append(_fileEnding, "          ");
                _builder_1.append("\">");
                _builder_1.newLineIfNotEmpty();
                _builder_1.append("    ");
                _builder_1.append("</test>");
                _builder_1.newLine();
                _builder_1.append("</adapt>");
                _builder_1.newLine();
                fileEndingCondition = _builder_1.toString();
                StringConcatenation _builder_2 = new StringConcatenation();
                _builder_2.append("<and>");
                _builder_2.newLine();
                _builder_2.append("   ");
                _builder_2.append("<with");
                _builder_2.newLine();
                _builder_2.append("         ");
                _builder_2.append("variable=\"activeEditor\">");
                _builder_2.newLine();
                _builder_2.append("      ");
                _builder_2.append("<instanceof");
                _builder_2.newLine();
                _builder_2.append("            ");
                _builder_2.append("value=\"org.eclipse.xtext.ui.editor.XtextEditor\">");
                _builder_2.newLine();
                _builder_2.append("      ");
                _builder_2.append("</instanceof>");
                _builder_2.newLine();
                _builder_2.append("   ");
                _builder_2.append("</with>");
                _builder_2.newLine();
                _builder_2.append("   ");
                _builder_2.append("<with");
                _builder_2.newLine();
                _builder_2.append("         ");
                _builder_2.append("variable=\"activeEditorInput\">");
                _builder_2.newLine();
                _builder_2.append("      ");
                _builder_2.append(fileEndingCondition, "      ");
                _builder_2.newLineIfNotEmpty();
                _builder_2.append("   ");
                _builder_2.append("</with>");
                _builder_2.newLine();
                _builder_2.append("</and>");
                _builder_2.newLine();
                xtextEditorCondition = _builder_2.toString();
            }
            StringConcatenation _builder_3 = new StringConcatenation();
            _builder_3.append("<extension");
            _builder_3.newLine();
            _builder_3.append("      ");
            _builder_3.append("point=\"org.eclipse.ui.commands\">");
            _builder_3.newLine();
            _builder_3.append("   ");
            _builder_3.append("<category");
            _builder_3.newLine();
            _builder_3.append("         ");
            _builder_3.append("description=\"");
            String _sourceModelClassSimple = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple, "         ");
            _builder_3.append(" diagrams\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("         ");
            _builder_3.append("id=\"");
            String _projectName = this.projectInfo.getProjectName();
            _builder_3.append(_projectName, "         ");
            _builder_3.append(".");
            String _sourceModelClassSimple_1 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_1, "         ");
            _builder_3.append("Diagrams\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("         ");
            _builder_3.append("name=\"");
            String _sourceModelClassSimple_2 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_2, "         ");
            _builder_3.append("Diagrams\">");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("   ");
            _builder_3.append("</category>");
            _builder_3.newLine();
            _builder_3.append("   ");
            _builder_3.append("<command");
            _builder_3.newLine();
            _builder_3.append("         ");
            _builder_3.append("categoryId=\"");
            String _projectName_1 = this.projectInfo.getProjectName();
            _builder_3.append(_projectName_1, "         ");
            _builder_3.append(".");
            String _sourceModelClassSimple_3 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_3, "         ");
            _builder_3.append("Diagrams\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("         ");
            _builder_3.append("defaultHandler=\"");
            String _transformationPackage_2 = this.projectInfo.getTransformationPackage();
            String _plus_4 = (_transformationPackage_2 + ".OpenDiagramHandler");
            _builder_3.append(_plus_4, "         ");
            _builder_3.append("\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("         ");
            _builder_3.append("description=\"Primitive helper command that opens KLighD-based ");
            String _sourceModelClassSimple_4 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_4, "         ");
            _builder_3.append(" diagrams.\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("         ");
            _builder_3.append("id=\"");
            String _projectName_2 = this.projectInfo.getProjectName();
            _builder_3.append(_projectName_2, "         ");
            _builder_3.append(".open");
            String _sourceModelClassSimple_5 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_5, "         ");
            _builder_3.append("Diagram\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("         ");
            _builder_3.append("name=\"Open ");
            String _sourceModelClassSimple_6 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_6, "         ");
            _builder_3.append(" diagram\">");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("   ");
            _builder_3.append("</command>");
            _builder_3.newLine();
            _builder_3.append("</extension>");
            _builder_3.newLine();
            _builder_3.newLine();
            _builder_3.append("<extension");
            _builder_3.newLine();
            _builder_3.append("      ");
            _builder_3.append("point=\"org.eclipse.ui.menus\">");
            _builder_3.newLine();
            _builder_3.append("   ");
            _builder_3.append("<menuContribution");
            _builder_3.newLine();
            _builder_3.append("         ");
            _builder_3.append("locationURI=\"popup:org.eclipse.ui.popup.any?before=additions\">");
            _builder_3.newLine();
            _builder_3.append("      ");
            _builder_3.append("<command");
            _builder_3.newLine();
            _builder_3.append("            ");
            _builder_3.append("commandId=\"");
            String _projectName_3 = this.projectInfo.getProjectName();
            _builder_3.append(_projectName_3, "            ");
            _builder_3.append(".open");
            String _sourceModelClassSimple_7 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_7, "            ");
            _builder_3.append("Diagram\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("            ");
            _builder_3.append("label=\"Open ");
            String _sourceModelClassSimple_8 = this.projectInfo.getSourceModelClassSimple();
            _builder_3.append(_sourceModelClassSimple_8, "            ");
            _builder_3.append(" diagram\"");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("            ");
            _builder_3.append("style=\"push\">");
            _builder_3.newLine();
            _builder_3.append("         ");
            _builder_3.append("<visibleWhen");
            _builder_3.newLine();
            _builder_3.append("               ");
            _builder_3.append("checkEnabled=\"false\">");
            _builder_3.newLine();
            _builder_3.append("            ");
            _builder_3.append("<or>");
            _builder_3.newLine();
            _builder_3.append("               ");
            _builder_3.append(xtextEditorCondition, "               ");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("               ");
            _builder_3.append("<iterate ifEmpty=\"false\" operator=\"or\">");
            _builder_3.newLine();
            _builder_3.append("                  ");
            _builder_3.append("<or>");
            _builder_3.newLine();
            _builder_3.append("                     ");
            _builder_3.append("<instanceof");
            _builder_3.newLine();
            _builder_3.append("                         ");
            _builder_3.append("value=\"");
            String _sourceModelClassFullyQualified =
                    this.projectInfo.getSourceModelClassFullyQualified();
            _builder_3.append(_sourceModelClassFullyQualified, "                         ");
            _builder_3.append("\">");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("                     ");
            _builder_3.append("</instanceof>");
            _builder_3.newLine();
            _builder_3.append("                     ");
            _builder_3.append(fileEndingCondition, "                     ");
            _builder_3.newLineIfNotEmpty();
            _builder_3.append("                  ");
            _builder_3.append("</or>");
            _builder_3.newLine();
            _builder_3.append("               ");
            _builder_3.append("</iterate>");
            _builder_3.newLine();
            _builder_3.append("            ");
            _builder_3.append("</or>");
            _builder_3.newLine();
            _builder_3.append("         ");
            _builder_3.append("</visibleWhen>");
            _builder_3.newLine();
            _builder_3.append("      ");
            _builder_3.append("</command>");
            _builder_3.newLine();
            _builder_3.append("   ");
            _builder_3.append("</menuContribution>");
            _builder_3.newLine();
            _builder_3.append("</extension>");
            _builder_3.newLine();
            _builder_3.newLine();
            final String menuContribution = _builder_3.toString();
            StringConcatenation _builder_4 = new StringConcatenation();
            _builder_4.append("</plugin>");
            _builder_4.newLine();
            final String end = _builder_4.toString();
            String _xifexpression = null;
            boolean _isCreateMenuContribution = this.projectInfo.isCreateMenuContribution();
            if (_isCreateMenuContribution) {
                _xifexpression = menuContribution;
            }
            String _plus_5 = (beginning + _xifexpression);
            _xblockexpression = this.writeToFile((_plus_5 + end), fileWriter, "plugin.xml");
        }
        return _xblockexpression;
    }

    private IFile contributeCommandHandler(
            final IProjectFactoryContributor.IFileCreator fileWriter) {
        IFile _xblockexpression = null;
        {
            String _xifexpression = null;
            boolean _isUseFileEnding = this.projectInfo.isUseFileEnding();
            if (_isUseFileEnding) {
                StringConcatenation _builder = new StringConcatenation();
                _builder.append("if (selection instanceof ITextSelection) {");
                _builder.newLine();
                _builder.append("    ");
                _builder.append("IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);");
                _builder.newLine();
                _builder.append("    ");
                _builder.newLine();
                _builder.append("    ");
                _builder.append(
                        "// because of the visibility expressions in the plugin.xml guarding the menu contributions");
                _builder.newLine();
                _builder.append("    ");
                _builder.append(
                        "//  we can conclude to have a selection stemming from an XtextEditor; thus...");
                _builder.newLine();
                _builder.append("    ");
                _builder.append(
                        "IDiagramWorkbenchPart diagramPart = DiagramViewManager.createView(");
                _builder.newLine();
                _builder.append("            ");
                _builder.append("\"");
                String _transformationPackage = this.projectInfo.getTransformationPackage();
                _builder.append(_transformationPackage, "            ");
                _builder.append(".");
                String _sourceModelClassSimple = this.projectInfo.getSourceModelClassSimple();
                _builder.append(_sourceModelClassSimple, "            ");
                _builder.append("Diagram\", \"");
                String _sourceModelClassSimple_1 = this.projectInfo.getSourceModelClassSimple();
                _builder.append(_sourceModelClassSimple_1, "            ");
                _builder.append(" Diagram\",");
                _builder.newLineIfNotEmpty();
                _builder.append("            ");
                _builder.append("XtextEditorUtil.getXtextModelAccessProxy(activeEditor));");
                _builder.newLine();
                _builder.newLine();
                _builder.append("    ");
                _builder.newLine();
                _builder.append("    ");
                _builder.append("if (diagramPart != null) {");
                _builder.newLine();
                _builder.append("        ");
                _builder.append(
                        "XtextEditorUtil.registerSelectionListener(activeEditor, diagramPart);");
                _builder.newLine();
                _builder.append("    ");
                _builder.append("}");
                _builder.newLine();
                _builder.newLine();
                _builder.append("} else ");
                _xifexpression = _builder.toString();
            } else {
                _xifexpression = "";
            }
            final String xtextEditorSupport = _xifexpression;
            String _xifexpression_1 = null;
            boolean _isUseFileEnding_1 = this.projectInfo.isUseFileEnding();
            if (_isUseFileEnding_1) {
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append("import org.eclipse.jface.text.ITextSelection;");
                _builder_1.newLine();
                _xifexpression_1 = _builder_1.toString();
            }
            final String importITextSelection = _xifexpression_1;
            StringConcatenation _builder_2 = new StringConcatenation();
            _builder_2.append("package ");
            String _transformationPackage_1 = this.projectInfo.getTransformationPackage();
            _builder_2.append(_transformationPackage_1);
            _builder_2.append(";");
            _builder_2.newLineIfNotEmpty();
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.core.commands.AbstractHandler;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.core.commands.ExecutionEvent;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.core.commands.ExecutionException;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.core.resources.IFile;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.core.runtime.IStatus;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.core.runtime.Status;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.emf.common.util.URI;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.emf.ecore.resource.Resource;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.emf.ecore.resource.ResourceSet;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.emf.ecore.util.EcoreUtil;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.jface.dialogs.MessageDialog;");
            _builder_2.newLine();
            _builder_2.append(importITextSelection);
            _builder_2.append("import org.eclipse.jface.viewers.ISelection;");
            _builder_2.newLineIfNotEmpty();
            _builder_2.append("import org.eclipse.jface.viewers.IStructuredSelection;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.ui.IEditorPart;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.ui.handlers.HandlerUtil;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.ui.statushandlers.StatusManager;");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;");
            _builder_2.newLine();
            _builder_2.append("import de.cau.cs.kieler.klighd.ui.DiagramViewManager;");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("/**");
            _builder_2.newLine();
            _builder_2.append(" ");
            _builder_2.append("* A simple handler for opening diagrams.");
            _builder_2.newLine();
            _builder_2.append(" ");
            _builder_2.append("*/");
            _builder_2.newLine();
            _builder_2.append("public class OpenDiagramHandler extends AbstractHandler {");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("    ");
            _builder_2.append("/**");
            _builder_2.newLine();
            _builder_2.append("     ");
            _builder_2.append("* {@inheritDoc}");
            _builder_2.newLine();
            _builder_2.append("     ");
            _builder_2.append("*/");
            _builder_2.newLine();
            _builder_2.append("    ");
            _builder_2.append(
                    "public Object execute(final ExecutionEvent event) throws ExecutionException {");
            _builder_2.newLine();
            _builder_2.append("        ");
            _builder_2
                    .append("final ISelection selection = HandlerUtil.getCurrentSelection(event);");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("        ");
            _builder_2.append(xtextEditorSupport, "        ");
            _builder_2.append("if (selection instanceof IStructuredSelection) {");
            _builder_2.newLineIfNotEmpty();
            _builder_2.append("            ");
            _builder_2.append(
                    "final IStructuredSelection sSelection  = (IStructuredSelection) selection;");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("            ");
            _builder_2.append("final Object firstElement = sSelection.getFirstElement();");
            _builder_2.newLine();
            _builder_2.append("            ");
            _builder_2.append("final Object model;");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("            ");
            _builder_2.append("if (firstElement instanceof IFile) {");
            _builder_2.newLine();
            _builder_2.append("                ");
            _builder_2.append("try {");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("final ResourceSet rs = new ResourceSetImpl();");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("final Resource r = rs.getResource(URI.createPlatformResourceURI(");
            _builder_2.newLine();
            _builder_2.append("                            ");
            _builder_2.append("((IFile) firstElement).getFullPath().toString(), true), true);");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("EcoreUtil.resolveAll(r);");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("if (r.getContents().size() > 0) {");
            _builder_2.newLine();
            _builder_2.append("                        ");
            _builder_2.append("model = r.getContents().get(0);");
            _builder_2.newLine();
            _builder_2.append("                        ");
            _builder_2.append("r.unload();");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("} else {");
            _builder_2.newLine();
            _builder_2.append("                        ");
            _builder_2.append("r.unload();");
            _builder_2.newLine();
            _builder_2.append("                        ");
            _builder_2.append("return null;");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("}");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("                ");
            _builder_2.append("} catch (Exception e) {");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("final String message = \"Could not load selected file.\";");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("StatusManager.getManager().handle(");
            _builder_2.newLine();
            _builder_2.append("                            ");
            _builder_2.append(
                    "new Status(IStatus.ERROR, this.getClass().getCanonicalName(), message, e),");
            _builder_2.newLine();
            _builder_2.append("                            ");
            _builder_2.append("StatusManager.SHOW);");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("return null;");
            _builder_2.newLine();
            _builder_2.append("                ");
            _builder_2.append("}");
            _builder_2.newLine();
            _builder_2.append("            ");
            _builder_2.append("} else {");
            _builder_2.newLine();
            _builder_2.append("                ");
            _builder_2.append("model = firstElement;");
            _builder_2.newLine();
            _builder_2.append("            ");
            _builder_2.append("}");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("            ");
            _builder_2.append("DiagramViewManager.createView(");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append("\"");
            String _transformationPackage_2 = this.projectInfo.getTransformationPackage();
            _builder_2.append(_transformationPackage_2, "                    ");
            _builder_2.append(".");
            String _sourceModelClassSimple_2 = this.projectInfo.getSourceModelClassSimple();
            _builder_2.append(_sourceModelClassSimple_2, "                    ");
            _builder_2.append("Diagram\", \"");
            String _sourceModelClassSimple_3 = this.projectInfo.getSourceModelClassSimple();
            _builder_2.append(_sourceModelClassSimple_3, "                    ");
            _builder_2.append(" Diagram\", model);");
            _builder_2.newLineIfNotEmpty();
            _builder_2.append("        ");
            _builder_2.append("} else {");
            _builder_2.newLine();
            _builder_2.append("            ");
            _builder_2.append(
                    "MessageDialog.openInformation(HandlerUtil.getActiveShell(event), \"Unsupported element\",");
            _builder_2.newLine();
            _builder_2.append("                    ");
            _builder_2.append(
                    "\"KLighD diagram synthesis is unsupported for the current selection \"");
            _builder_2.newLine();
            _builder_2.append("                            ");
            _builder_2.append("+ selection.toString() + \".\");");
            _builder_2.newLine();
            _builder_2.append("        ");
            _builder_2.append("}");
            _builder_2.newLine();
            _builder_2.append("        ");
            _builder_2.append("return null;");
            _builder_2.newLine();
            _builder_2.append("    ");
            _builder_2.append("}");
            _builder_2.newLine();
            _builder_2.append("}");
            _builder_2.newLine();
            String _replace = this.projectInfo.getTransformationPackage().replace(".", "/");
            String _plus = (KlighdWizardSetup.SRC_FOLDER + _replace);
            String _plus_1 = (_plus + "/OpenDiagramHandler.java");
            _xblockexpression = this.writeToFile(_builder_2, fileWriter, _plus_1);
        }
        return _xblockexpression;
    }

    private IFile contributeHandlerHelperClass(
            final IProjectFactoryContributor.IFileCreator fileWriter) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("package ");
        String _transformationPackage = this.projectInfo.getTransformationPackage();
        _builder.append(_transformationPackage);
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("import java.util.Map;");
        _builder.newLine();
        _builder.append("import java.util.HashMap;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import org.eclipse.emf.ecore.EObject;");
        _builder.newLine();
        _builder.append("import org.eclipse.jface.viewers.ISelection;");
        _builder.newLine();
        _builder.append("import org.eclipse.ui.IEditorPart;");
        _builder.newLine();
        _builder.append("import org.eclipse.ui.IPartListener;");
        _builder.newLine();
        _builder.append("import org.eclipse.ui.ISelectionListener;");
        _builder.newLine();
        _builder.append("import org.eclipse.ui.IWorkbenchPage;");
        _builder.newLine();
        _builder.append("import org.eclipse.ui.IWorkbenchPart;");
        _builder.newLine();
        _builder.append("import org.eclipse.xtext.nodemodel.INode;");
        _builder.newLine();
        _builder.append("import org.eclipse.xtext.nodemodel.util.NodeModelUtils;");
        _builder.newLine();
        _builder.append("import org.eclipse.xtext.resource.XtextResource;");
        _builder.newLine();
        _builder.append("import org.eclipse.xtext.ui.editor.XtextEditor;");
        _builder.newLine();
        _builder.append("import org.eclipse.xtext.util.concurrent.IUnitOfWork;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import com.google.common.base.Function;");
        _builder.newLine();
        _builder.append("import com.google.common.collect.Iterators;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.IKlighdSelection;");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.ISourceProxy;");
        _builder.newLine();
        _builder.append("import de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public class XtextEditorUtil {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("     ");
        _builder.append(
                "* Factory method providing an implementation of {@link ISourceProxy} enabling the execution of");
        _builder.newLine();
        _builder.append("     ");
        _builder.append(
                "* KLighD operations properly within Xtext\'s {@link IUnitOfWork IUnitOfWorks}.");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("* @param editorPart");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("*            the {@link XtextEditor} whose model shall be accessed");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("* @return the desired {@link ISourceProxy} implementation");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(
                "public static ISourceProxy getXtextModelAccessProxy(final IEditorPart editorPart) {");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("if (!(editorPart instanceof XtextEditor)) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("// if a non-Xtext editor is given don\'t to anything;");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("return null;");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("final XtextEditor xtextEditor = (XtextEditor) editorPart;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// because Xtext editors protect the model (resource) by a transaction mechanism");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "//  an instance of ISourceProxy is returned instead of returning the model directly");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("return new ISourceProxy() {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append(
                "// The implementation of \'execute(...)\' implements Xtext\'s transaction mechanism;");
        _builder.newLine();
        _builder.append("            ");
        _builder.append(
                "// KLighD will invoke this method and provide a function wrapping the KLighD internal");
        _builder.newLine();
        _builder.append("            ");
        _builder.append(
                "//  functionality; the function\'s result must be returned by \'execute(...)\' method");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append("public <T> T execute(final Function<Object, T> function) {");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("if (xtextEditor.getDocument() == null) {");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("// stop if for some reason there\'s no document (shouldn\'t happen)");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("return null;");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("                ");
        _builder.append("// perform a read operation on the model and execute KLighD\'s tasks");
        _builder.newLine();
        _builder.append("                ");
        _builder.append(
                "return xtextEditor.getDocument().readOnly(new IUnitOfWork<T, XtextResource>() {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("public T exec(final XtextResource res) throws Exception {");
        _builder.newLine();
        _builder.append("                        ");
        _builder.append("// return just \'null\' if there\'s no model within the resource");
        _builder.newLine();
        _builder.append("                        ");
        _builder.append("//  otherwise apply the function provided by KLighD");
        _builder.newLine();
        _builder.append("                        ");
        _builder.append("return res.getContents().isEmpty() ? null :");
        _builder.newLine();
        _builder.append("                            ");
        _builder.append("function.apply(res.getContents().get(0));");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("});");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("};");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("    ");
        _builder.append(
                "private static final Map<XtextEditor, ISelectionListener> selectionListeners = new HashMap<>();");
        _builder.newLine();
        _builder.newLine();
        _builder.newLine();
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("     ");
        _builder.append(
                "* Registers an {@link ISelectionListener} dedicated to {@code diagramPart}");
        _builder.newLine();
        _builder.append("     ");
        _builder.append(
                "* in the Eclipse selection service. This selection listener listens for selections");
        _builder.newLine();
        _builder.append("     ");
        _builder.append(
                "* in {@code diagramPart} and is in charge of highlighting the corresponding");
        _builder.newLine();
        _builder.append("     ");
        _builder.append(
                "* declarations in {@code editorPart}, which is supposed to be an {@link XtextEditor}.<br>");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("* <br>");
        _builder.newLine();
        _builder.append("     ");
        _builder.append(
                "* The registered listeners are tracked and de-registered if {@code editorPart} is closed.");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("* @param editorPart");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("*            is supposed to be a valid {@link XtextEditor}");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("* @param diagramPart");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("*            the diagram view part to be listened for selection changes");
        _builder.newLine();
        _builder.append("     ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(
                "public static void registerSelectionListener(IEditorPart editorPart, IDiagramWorkbenchPart diagramPart) {");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("if (!(editorPart instanceof XtextEditor)) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("return;");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("final XtextEditor xtextEditor = (XtextEditor) editorPart;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// the selection listeners are installed corresponding to the \'xtextEditors\'");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("//  that provide the source (domain) models");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// if there is already a listener tracked with the given editor, we\'re done");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("if (selectionListeners.containsKey(xtextEditor)) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("return;");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// otherwise instantiate a new listener, ...");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("final ISelectionListener selectionListener = new ISelectionListener() {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("            ");
        _builder.append(
                "public void selectionChanged(IWorkbenchPart part, ISelection selection) {");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("// first check for the correct selection type (just for safety purposes)");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("if (!(selection instanceof IKlighdSelection)) {");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("return;");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("                ");
        _builder.append("final IKlighdSelection klighdSelection = (IKlighdSelection) selection;");
        _builder.newLine();
        _builder.newLine();
        _builder.append("                ");
        _builder.append("// obtain the first selected diagram element and ask KLighD");
        _builder.newLine();
        _builder.append("                ");
        _builder.append(
                "//  (i.e. the diagram\'s ViewContext) for the corresponding source element");
        _builder.newLine();
        _builder.append("                ");
        _builder.append(
                "final Object selectedDomainElement = klighdSelection.getViewContext().getSourceElement(");
        _builder.newLine();
        _builder.append("                        ");
        _builder.append("Iterators.getNext(klighdSelection.diagramElementsIterator(), null));");
        _builder.newLine();
        _builder.newLine();
        _builder.append("                ");
        _builder.append(
                "// try to highlight the corresponding definition if \'selectedDomainElement\' is");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("//  a valid model element, i.e. non null, for example");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("if (selectedDomainElement instanceof EObject) {");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("highlightSelection(xtextEditor, (EObject) selectedDomainElement);");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("};");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// ... keep it in mind, ...");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("selectionListeners.put(xtextEditor, selectionListener);");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// ... compose the partId from primary and secondary id as required by the selection service");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "//  (the KLighD DiagramViewPart is a multi view; black magic ... *hoo*), ...");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "final String partId = DiagramViewPart.VIEW_ID + \":\" + diagramPart.getPartId();");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// ... and register the listener in the platform.");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("final IWorkbenchPage diagramPartPage = diagramPart.getSite().getPage();");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("diagramPartPage.addSelectionListener(partId, selectionListener);");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// For properly de-installing the selection listener we need to keep track of the editor. If");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "//  gets closed the selection listener gets obsolete as there\'s nothing to highlight any more.");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// Thus, we register an IPartListener that listens for the closure of the editor.");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "// It reveals the selection listener to be removed from the memory map, removes it from the");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("//  platform (selection service), and finally de-installes itself.");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("final IWorkbenchPage editorPartPage = xtextEditor.getSite().getPage();");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("editorPartPage.addPartListener(new IPartListener() {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("public void partClosed(IWorkbenchPart part) {");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("if (part == xtextEditor) {");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("final ISelectionListener l = selectionListeners.remove(xtextEditor);");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("diagramPartPage.removeSelectionListener(partId, l);");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("editorPartPage.removePartListener(this);");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("public void partOpened(IWorkbenchPart part) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("public void partDeactivated(IWorkbenchPart part) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("public void partBroughtToTop(IWorkbenchPart part) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("            ");
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("public void partActivated(IWorkbenchPart part) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("});");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.newLine();
        _builder.append("    ");
        _builder.append(
                "private static void highlightSelection(XtextEditor xtextEditor, EObject element) {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// ask \'xtextEditor\' for the position of \'element\'s definition");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("final Integer[] elementData = xtextEditor.getDocument().readOnly(");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("new IUnitOfWork<Integer[], XtextResource>() {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("public Integer[] exec(final XtextResource state) throws Exception {");
        _builder.newLine();
        _builder.newLine();
        _builder.append("                        ");
        _builder.append("final INode node = NodeModelUtils.findActualNodeFor(element);");
        _builder.newLine();
        _builder.append("                        ");
        _builder.append("return node == null ? null");
        _builder.newLine();
        _builder.append("                                ");
        _builder.append(": new Integer[] { node.getOffset(), node.getLength() };");
        _builder.newLine();
        _builder.append("                    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("                ");
        _builder.append("});");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("if (elementData == null) {");
        _builder.newLine();
        _builder.append("            ");
        _builder.append("return;");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// set the selection to that area");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "xtextEditor.getInternalSourceViewer().setSelectedRange(elementData[0], elementData[1]);");
        _builder.newLine();
        _builder.append("        ");
        _builder.append(
                "xtextEditor.getInternalSourceViewer().revealRange(elementData[0], elementData[1]);");
        _builder.newLine();
        _builder.newLine();
        _builder.append("        ");
        _builder.append("// bring the editor to foreground");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("xtextEditor.getSite().getPage().bringToTop(xtextEditor);");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("}");
        _builder.newLine();
        String _replace = this.projectInfo.getTransformationPackage().replace(".", "/");
        String _plus = (KlighdWizardSetup.SRC_FOLDER + _replace);
        String _plus_1 = (_plus + "/XtextEditorUtil.java");
        return this.writeToFile(_builder, fileWriter, _plus_1);
    }

    protected IFile writeToFile(final CharSequence chrSeq,
            final IProjectFactoryContributor.IFileCreator fCreator, final String fileName) {
        return fCreator.writeToFile(chrSeq, fileName);
    }
}
